package com.doranco.examspring.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doranco.examspring.controller.payload.Payload;
import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.model.entity.Borrowing;
import com.doranco.examspring.repository.AuthorRepository;
import com.doranco.examspring.repository.BookRepository;
import com.doranco.examspring.repository.BorrowingRepository;

@RestController
@RequestMapping("/api")
public class ApiController {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final BorrowingRepository borrowingRepository;
	
	public ApiController(AuthorRepository authorRepository, BookRepository bookRepository,
			BorrowingRepository borrowingRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.borrowingRepository = borrowingRepository;
	}
	
	
	
	
	
	//BOOK MANAGER
	
    @PostMapping("/book")
    public ResponseEntity<Payload> addBook(@RequestBody Book book)
    {
        var data = this.bookRepository.save(book);
        var payload = new Payload("Add new book", data);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/book/{id}")
    public ResponseEntity<Payload> deleteBook(@PathVariable int id)
    {
        bookRepository.deleteById(id);
        return new ResponseEntity<>(new Payload("Book deleted"), HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/book/search")
    public ResponseEntity<Payload> searchBook(@RequestParam String title, @RequestParam String author, @RequestParam String publisher)
    {
        System.out.println("Title :" + title);
        System.out.println("Author : " + author);
        System.out.println("Publisher : " + publisher);
        Payload payload = new Payload();
        var data = bookRepository.findByTitleContainingOrAuthorContainingOrPublisherContaining(title, author, publisher);
        System.out.println(data);
        if (data.isEmpty()) {
            payload.setMessage("Book Not found ...");
            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        }
        payload.setMessage("Get All book by title or author or publisher");
        payload.setData(data);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
    
    
    
    
    
    //AUTHOR MANAGER
    
    @PostMapping("/author")
    public ResponseEntity<Payload> addAuthor(@RequestBody Author author)
    {
        var data = this.authorRepository.save(author);
        var payload = new Payload("Add new author", data);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/author/{id}")
    public ResponseEntity<Payload> deleteAuthor(@PathVariable int id)
    {
        authorRepository.deleteById(id);
        return new ResponseEntity<>(new Payload("Author deleted"), HttpStatus.NO_CONTENT);
    }
    
    
    
    
    
    //BORROWING MANAGER
    
    @PostMapping("/author")
    public ResponseEntity<Payload> addBorrowing(@RequestBody Borrowing borrowing)
    {
        var data = this.borrowingRepository.save(borrowing);
        var payload = new Payload("Add new borrowing", data);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }
    
    @GetMapping("/borrowing/search")
    public ResponseEntity<Payload> searchBorrowing(@RequestParam LocalDate borrowDate, @RequestParam LocalDate dueDate)
    {
        System.out.println("Borrow Date :" + borrowDate);
        System.out.println("Due Date :" + dueDate);
        Payload payload = new Payload();
        var data = borrowingRepository.findByBorrowDateContainingOrDueDateContaining(borrowDate, dueDate);
        System.out.println(data);
        if (data.isEmpty()) {
            payload.setMessage("Borrowing ...");
            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        }
        payload.setMessage("Get All book by title or author or publisher");
        payload.setData(data);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
    
    @DeleteMapping("/borrowing/{id}")
    public ResponseEntity<Payload> deleteBorrowing(@PathVariable int id)
    {
        borrowingRepository.deleteById(id);
        return new ResponseEntity<>(new Payload("Borrowing deleted"), HttpStatus.NO_CONTENT);
    }
    
    
}
