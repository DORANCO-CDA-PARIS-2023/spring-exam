package com.doranco.examspring.controller;

import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.model.repository.BookRepository;
import com.doranco.examspring.controller.payload.Payload;
import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.model.repository.AuthorRepository;

import com.doranco.examspring.model.entity.Borrowing;
import com.doranco.examspring.model.repository.BorrowingRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

	private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BorrowingRepository borrowingRepository;

    @Autowired
    public ApiController(BookRepository bookRepository, AuthorRepository authorRepository, BorrowingRepository borrowingRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.borrowingRepository = borrowingRepository;
    }
    
	@PostMapping("/books")
    public ResponseEntity<Payload> addBook(@RequestBody Book book) {
        var data = this.bookRepository.save(book);
        var payload = new Payload("Add new contact", data);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }
	
	@PutMapping("/books/{bookId}")
    public ResponseEntity<Payload> updateBook(@PathVariable Long bookId, @RequestBody Book book) {
        if (!bookRepository.existsById(bookId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        book.setId(bookId);
        var data = this.bookRepository.save(book);
        var payload = new Payload("Add new contact", data);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }
	
	@DeleteMapping("/books/{bookId}")
    public ResponseEntity<Payload> deleteBook(@PathVariable Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        bookRepository.deleteById(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> searchBooks(
	        @RequestParam(required = false) String title,
	        @RequestParam(required = false) String author,
	        @RequestParam(required = false) String publisher) {

	    List<Book> foundBooks;

	    if (title != null) {
	        foundBooks = bookRepository.findByTitle(title);
	    } else if (author != null) {
	        foundBooks = bookRepository.findByAuthor(author);
	    } else if (publisher != null) {
	        foundBooks = bookRepository.findByPublisher(publisher);
	    } else {
	        foundBooks = bookRepository.findAll();
	    }

	    return new ResponseEntity<>(foundBooks, HttpStatus.OK);
	}
	
	@PostMapping("/authors")
    public ResponseEntity<Payload> addAuthor(@RequestBody Author author) {
        var data = this.authorRepository.save(author);
        var payload = new Payload("Add new contact", data);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }
	
	@PutMapping("/authors/{authorId}")
    public ResponseEntity<Payload> updateAuthor(@PathVariable Long authorId, @RequestBody Author authorDetails) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        
        if (optionalAuthor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Author author = optionalAuthor.get();
        author.setName(authorDetails.getName());
        author.setBiography(authorDetails.getBiography());

        var data = this.authorRepository.save(author);
        var payload = new Payload("Add new contact", data);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
	
	@DeleteMapping("/authors/{authorId}")
    public ResponseEntity<Payload> deleteAuthor(@PathVariable Long authorId) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        
        if (optionalAuthor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        authorRepository.deleteById(authorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@GetMapping("/authors")
	public ResponseEntity<List<Author>> searchAuthorsByName(@RequestParam(required = false) String name) {
	    List<Author> authors;
	    
	    if (name != null) {
	        authors = authorRepository.findByName(name);
	    } else {
	        authors = authorRepository.findAll();
	    }
	    
	    return new ResponseEntity<>(authors, HttpStatus.OK);
	}
	
	@PostMapping("/borrowings")
    public ResponseEntity<Borrowing> addBorrowing(@RequestBody Borrowing borrowing) {
        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        return new ResponseEntity<>(savedBorrowing, HttpStatus.CREATED);
    }
	
	@PutMapping("/borrowings/{borrowingId}")
    public ResponseEntity<Borrowing> updateBorrowing(@PathVariable Long borrowingId, @RequestBody Borrowing borrowingDetails) {
        Optional<Borrowing> optionalBorrowing = borrowingRepository.findById(borrowingId);

        if (optionalBorrowing.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Borrowing borrowing = optionalBorrowing.get();
        borrowing.setBookId(borrowingDetails.getBookId());
        borrowing.setBorrowDate(borrowingDetails.getBorrowDate());
        borrowing.setDueDate(borrowingDetails.getDueDate());

        Borrowing updatedBorrowing = borrowingRepository.save(borrowing);
        return new ResponseEntity<>(updatedBorrowing, HttpStatus.OK);
    }
	
	@DeleteMapping("/borrowings/{borrowingId}")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable Long borrowingId) {
        Optional<Borrowing> optionalBorrowing = borrowingRepository.findById(borrowingId);

        if (optionalBorrowing.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        borrowingRepository.deleteById(borrowingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@GetMapping("/borrowings")
    public ResponseEntity<List<Borrowing>> searchBorrowingsByStatus(@RequestParam(required = false) String status) {
        if (status == null || status.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Borrowing> borrowings;
        if (status.equalsIgnoreCase("en_cours")) {
            borrowings = borrowingRepository.findByDueDateAfterNow();
        } else if (status.equalsIgnoreCase("passes")) {
            borrowings = borrowingRepository.findByDueDateBeforeNow();
        } else {
        	borrowings = borrowingRepository.findAll();
        }

        return new ResponseEntity<>(borrowings, HttpStatus.OK);
    }
}