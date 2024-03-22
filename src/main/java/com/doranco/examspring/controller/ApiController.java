package com.doranco.examspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.doranco.examspring.controller.payload.Payload;
import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.repository.IBookRepository;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.repository.IAuthorRepository;
import com.doranco.examspring.model.entity.Borrowing;
import com.doranco.examspring.repository.IBorrowingRepository;


@RestController
@RequestMapping("/api")
public class ApiController {

    private final IBookRepository bookRepository;
    private final IAuthorRepository authorRepository;
    private final IBorrowingRepository borrowingRepository;

    public ApiController(IBookRepository bookRepository, IAuthorRepository authorRepository, IBorrowingRepository borrowingRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.borrowingRepository = borrowingRepository;
    }

    @PostMapping("/books")
    public ResponseEntity<Payload> addBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Payload("Book added successfully", savedBook));
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Payload> updateBook(@PathVariable Integer bookId, @RequestBody Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPublisher(bookDetails.getPublisher());
            book.setPublicationYear(bookDetails.getPublicationYear());
            book.setPageCount(bookDetails.getPageCount());
            final Book updatedBook = bookRepository.save(book);
            return ResponseEntity.ok(new Payload("Book updated successfully", updatedBook));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher) {
        if (title != null) {
            List<Book> books = bookRepository.findByTitle(title);
            return ResponseEntity.ok(books);
        }
        if (author != null) {
            List<Book> books = bookRepository.findByAuthor(author);
            return ResponseEntity.ok(books);
        }
        if (publisher != null) {
            List<Book> books = bookRepository.findByPublisher(publisher);
            return ResponseEntity.ok(books);
        }
        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Payload> deleteBook(@PathVariable Integer bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(bookId);
            return ResponseEntity.ok(new Payload("Book deleted successfully", null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ------------------- author -------------------

    @PostMapping("/author")
    public ResponseEntity<Payload> addAuthor(@RequestBody Author author) {
        Author savedAuthor = authorRepository.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Payload("Author added successfully", savedAuthor));
    }

    @PutMapping("/authors/{authorId}")
    public ResponseEntity<Payload> updateAuthor(@PathVariable Integer AuthorId, @RequestBody Author authorDetails) {
        Optional<Author> optionalAuthor = authorRepository.findById(AuthorId);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setName(authorDetails.getName());
            author.setBiography(authorDetails.getBiography());
            final Author updatedAuthor = authorRepository.save(author);
        return ResponseEntity.ok(new Payload("Author updated successfully", updatedAuthor));
        } else {
            return ResponseEntity.notFound().build();
        }
    } 
    @DeleteMapping("/authors/{authorId}")
    public ResponseEntity<Payload> deleteAuthor(@PathVariable Integer authorId) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()) {
            authorRepository.deleteById(authorId);
            return ResponseEntity.ok(new Payload("Author deleted successfully", null));
        } else {
            return ResponseEntity.notFound().build();
        }
    } 
    @GetMapping("/authorByName")
    public ResponseEntity<List<Author>> getAuthorByName(@RequestParam(required = false) String name) {
        if (name != null) {
            List<Author> authors = authorRepository.findByName(name);
            return ResponseEntity.ok(authors);
        }
        List<Author> authors = authorRepository.findAll();
        return ResponseEntity.ok(authors);
    }

    // ------------------- Borrowing -------------------

    @PostMapping("/borrowing")
    public ResponseEntity<Payload> addBorrowing(@RequestBody Borrowing borrowing) {
        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Payload("Borrowing added successfully", savedBorrowing));
    }
    @DeleteMapping("/borrowing/{borrowingId}")
    public ResponseEntity<Payload> deleteBorrowing(@PathVariable Integer borrowingId) {
        Optional<Borrowing> optionalBorrowing = borrowingRepository.findById(borrowingId);
        if (optionalBorrowing.isPresent()) {
            borrowingRepository.deleteById(borrowingId);
            return ResponseEntity.ok(new Payload("Borrowing deleted successfully", null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
