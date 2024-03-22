package com.doranco.examspring.controller;

import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class ApiController {

    private final BookRepository bookRepository;

    @Autowired
    public ApiController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        if (savedBook != null) {
            return new ResponseEntity<>("Livre ajouter", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Echec dans l'ajout du livre", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
        if (!bookRepository.existsById(bookId)) {
            return new ResponseEntity<>("Livre inexistant", HttpStatus.NOT_FOUND);
        }
        updatedBook.setId(bookId);
        bookRepository.save(updatedBook);

        return new ResponseEntity<>("Livre mis a jour", HttpStatus.OK);
    }
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return new ResponseEntity<>("Livre supprimé", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Livre inexistant", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String author,
                                                  @RequestParam(required = false) String publisher) {
        List<Book> books;
        if (title != null || author != null || publisher != null) {
            books = bookRepository.findByTitleOrAuthorOrPublisher(title, author, publisher);
            if (!books.isEmpty()) {
                return new ResponseEntity<>(books, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
