package com.doranco.examspring.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.repository.BookRepository;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

   
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

   
    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book book) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (!optionalBook.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Book existingBook = optionalBook.get();
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPublisher(book.getPublisher());
        existingBook.setPublicationYear(book.getPublicationYear());
        existingBook.setPageCount(book.getPageCount());

        Book updatedBook = bookRepository.save(existingBook);
        return ResponseEntity.ok(updatedBook);
    }

    
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        bookRepository.deleteById(bookId);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher) {
        List<Book> books;
        if (title != null) {
            books = bookRepository.findByTitleContaining(title);
        } else if (author != null) {
            books = bookRepository.findByAuthorContaining(author);
        } else if (publisher != null) {
            books = bookRepository.findByPublisherContaining(publisher);
        } else {
            books = bookRepository.findAll();
        }
        return ResponseEntity.ok(books);
    }
}
