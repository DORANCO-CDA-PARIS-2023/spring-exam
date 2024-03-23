package com.doranco.examspring.controller.api;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.doranco.examspring.controller.api.payload.Payload;
import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.repository.IBookRepository;

@RestController
@RequestMapping("/books")
public class ApiBookController {
	
	private final IBookRepository bookRepository;
	
	public ApiBookController(IBookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@PostMapping("")
	public ResponseEntity<Payload> create(@RequestBody Book book) {
		Payload payload = new Payload();
		book = bookRepository.save(book);
		if (book == null) {
			payload.setMessage("Book not created");
			return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
		}
		payload.setData(book);
		payload.setMessage("Book created");
		return new ResponseEntity<>(payload, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Payload> updateBook(@RequestBody Book updatedBook, @PathVariable Long id) {
	    Optional<Book> optionalBook = bookRepository.findById(id);
	    Payload payload = new Payload();
	    if (optionalBook.isEmpty()) {
	    	payload.setMessage("Book not found");
	    	return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
	    }
	    Book existingBook = optionalBook.get();
	    existingBook.setTitle(updatedBook.getTitle());
	    existingBook.setAuthor(updatedBook.getAuthor());
	    existingBook.setPublisher(updatedBook.getPublisher());
	    existingBook.setPublicationYear(updatedBook.getPublicationYear());
	    existingBook.setPageCount(updatedBook.getPageCount());
	    existingBook = bookRepository.save(existingBook);
		payload.setData(existingBook);
		payload.setMessage("Book updated");
		return new ResponseEntity<>(payload, HttpStatus.OK);
	
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<Payload> deleteBook(@PathVariable Long id) {
		Payload payload = new Payload();
	    Optional<Book> optionalBook = bookRepository.findById(id);
	    if (optionalBook.isEmpty()) {
	    	payload.setMessage("Book not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body(payload);
		} else {
			Book book = optionalBook.get();
			bookRepository.delete(book);
			payload.setMessage("Book deleted");
			return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
		}
	}

	
	// http://localhost:8080/books/search?keyword=Le Peti
	@GetMapping("/search")
	public ResponseEntity<Payload> searchBooks(
	        @RequestParam(required = false) String keyword) {
	    
	    List<Book> books = bookRepository.findByTitleOrAuthorOrPublisher(keyword);
	    Payload payload = new Payload();
	    if (books.isEmpty()) {
			payload.setMessage("No books found");
			return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
	    }
		payload.setData(books);
		payload.setMessage("Books found");
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}

	
	
	
	
	
	
	
	
	
	
	

}
