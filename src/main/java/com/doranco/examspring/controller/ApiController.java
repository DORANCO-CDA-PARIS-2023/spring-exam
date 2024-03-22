package com.doranco.examspring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.model.entity.Borrowing;
import com.doranco.examspring.repository.AuthorRepository;
import com.doranco.examspring.repository.BookRepository;
import com.doranco.examspring.repository.BorrowingRepository;
import com.doranco.examspring.controller.payload.Payload;

@RestController
public class ApiController {

    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    private BorrowingRepository borrowingRepository;

    public ApiController(BookRepository bookRepository, AuthorRepository authorRepository, BorrowingRepository borrowingRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.borrowingRepository = borrowingRepository;
    }

    @PostMapping("/authors")
    public ResponseEntity<Payload> addAuthor(@RequestBody Author author) {
        Author savedAuthor = authorRepository.save(author);
        return new ResponseEntity<>(new Payload("Author Created", savedAuthor), HttpStatus.CREATED);
    }

    @PutMapping("/authors/{authorId}")
    public ResponseEntity<Payload> updateAuthor(@PathVariable Long authorId, @RequestBody Author author) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()) {
            Author existingAuthor = optionalAuthor.get();
            existingAuthor.setName(author.getName());
            existingAuthor.setBiography(author.getBiography());
            authorRepository.save(existingAuthor);
            return new ResponseEntity<>(new Payload("Author Updated", existingAuthor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Payload("Author Not Found"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/authors/{authorId}")
    public ResponseEntity<Payload> deleteAuthor(@PathVariable Long authorId) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()) {
            authorRepository.deleteById(authorId);
            return new ResponseEntity<>(new Payload("Author Deleted"), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new Payload("Author Not Found"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/authors")
    public ResponseEntity<Payload> searchAuthors(@RequestParam(required = false) String name) {
        List<Author> foundAuthors;
        if (name != null) {
            foundAuthors = authorRepository.findByName(name);
        } else {
            foundAuthors = authorRepository.findAll();
        }
        return new ResponseEntity<>(new Payload("Authors Found", foundAuthors), HttpStatus.OK);
    }

    @PostMapping("/borrowings")
    public ResponseEntity<Payload> addBorrowing(@RequestBody Borrowing borrowing) {
        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        return new ResponseEntity<>(new Payload("Borrowing Created", savedBorrowing), HttpStatus.CREATED);
    }

    @PutMapping("/borrowings/{borrowingId}")
    public ResponseEntity<Payload> updateBorrowing(@PathVariable Long borrowingId, @RequestBody Borrowing borrowing) {
        Optional<Borrowing> optionalBorrowing = borrowingRepository.findById(borrowingId);
        if (optionalBorrowing.isPresent()) {
            Borrowing existingBorrowing = optionalBorrowing.get();
            existingBorrowing.setBookId(borrowing.getBookId());
            existingBorrowing.setBorrowDate(borrowing.getBorrowDate());
            existingBorrowing.setDueDate(borrowing.getDueDate());
            borrowingRepository.save(existingBorrowing);
            return new ResponseEntity<>(new Payload("Borrowing Updated", existingBorrowing), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Payload("Borrowing Not Found"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/borrowings/{borrowingId}")
    public ResponseEntity<Payload> deleteBorrowing(@PathVariable Long borrowingId) {
        Optional<Borrowing> optionalBorrowing = borrowingRepository.findById(borrowingId);
        if (optionalBorrowing.isPresent()) {
            borrowingRepository.deleteById(borrowingId);
            return new ResponseEntity<>(new Payload("Borrowing Deleted"), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new Payload("Borrowing Not Found"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/borrowings")
    public ResponseEntity<Payload> searchBorrowings(@RequestParam(required = false) String status) {
        List<Borrowing> foundBorrowings;
        
        if (status != null) {
            if (status.equals("en cours")) {
                foundBorrowings = borrowingRepository.findBorrowingsInProgress();
            } else {
                foundBorrowings = borrowingRepository.findCompletedBorrowings();
            }
        } else {
            foundBorrowings = borrowingRepository.findAll();
        }

        return new ResponseEntity<>(new Payload("Borrowings Found", foundBorrowings), HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Payload> addBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(new Payload(" 201 Created", savedBook), HttpStatus.CREATED);
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Payload> updateBook(@PathVariable Long bookId, @RequestBody Book book) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPublisher(book.getPublisher());
            existingBook.setPublicationYear(book.getPublicationYear());
            existingBook.setPageCount(book.getPageCount());
            bookRepository.save(existingBook);
            return new ResponseEntity<>(new Payload("Book Updated", existingBook), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Payload("Book Not Found"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Payload> deleteBook(@PathVariable Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(bookId);
            return new ResponseEntity<>(new Payload("Book Deleted"), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new Payload("Book Not Found"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/books")
    public ResponseEntity<Payload> searchBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) String publisher) {
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
        return new ResponseEntity<>(new Payload("Books Found", foundBooks), HttpStatus.OK);
    }
}