package com.doranco.examspring.controller.api;


import java.util.List;
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
		return new ResponseEntity<>(payload, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Payload> update(@RequestBody Book book, @PathVariable Long id) {
		Payload payload = new Payload();
		Book olderBook = bookRepository.findById(id).orElse(null);
		if (olderBook == null) {
			payload.setMessage("Book not faond");
			return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
		}
		olderBook.setTitle(book.getTitle());
		olderBook.setAuthor(book.getAuthor());
		olderBook.setPublisher(book.getPublisher());
		olderBook.setPublicationYear(book.getPublicationYear());
		olderBook.setPageCount(book.getPageCount());
		payload.setData(olderBook);
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Payload> delete(@PathVariable Long id) {
		Payload payload = new Payload();
		Book book = bookRepository.findById(id).orElse(null);
		if (book == null) {
			payload.setMessage("Book not found");
			return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
		}
		bookRepository.delete(book);
		payload.setMessage("Book deleted");
		return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/search")
	public ResponseEntity<Payload> search(@RequestParam String title, @RequestParam String author,
			@RequestParam String publisher) {
		List<Book> books = bookRepository.findByTitleOrAuthorOrPublisher(title, author, publisher);
		Payload payload = new Payload();
		if (books.isEmpty()) {
			payload.setMessage("No book found");
			return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
		}
		payload.setData(books);
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	

}
