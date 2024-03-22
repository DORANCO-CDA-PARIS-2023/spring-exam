package com.doranco.examspring.controller;

import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.model.entity.Borrowing;
import com.doranco.examspring.repo.AuthorRepo;
import com.doranco.examspring.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final List<Book> books = new ArrayList<>();
    private final List<Author> authors = new ArrayList<>();
    private final List<Borrowing> loans = new ArrayList<>();
    private final AtomicLong bookIdGenerator = new AtomicLong(1);
    private final AtomicLong authorIdGenerator = new AtomicLong(1);

    BookRepo bookRepo;
    AuthorRepo authorRepo;
    @Autowired
    public ApiController(BookRepo bookRepo,AuthorRepo authorRepo){
        this.bookRepo=bookRepo;
        this.authorRepo=authorRepo;
    }



    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {

        books.add(newBook);
        bookRepo.add(newBook);
        System.out.println(newBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
        Book existingBook = books.stream().filter(book -> book.getId().equals(bookId)).findFirst().orElse(null);
        if (existingBook != null) {
            // Update the existing book
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPublisher(updatedBook.getPublisher());
            existingBook.setPublicationYear(updatedBook.getPublicationYear());
            existingBook.setPageCount(updatedBook.getPageCount());
            return ResponseEntity.ok(existingBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        Book bookToDelete = findBookById(bookId);
        if (bookToDelete != null) {
            books.remove(bookToDelete);
            bookRepo.delete(bookToDelete);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    private Book findBookById(Long bookId) {
        return books.stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst()
                .orElse(null);
    }
    @GetMapping("/books?title={title}&author={author}&publisher={publisher}")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher) {
        List<Book> filteredBooks = books.stream()
                .filter(book -> title == null || book.getTitle().contains(title))
                .filter(book -> author == null || book.getAuthor().contains(author))
                .filter(book -> publisher == null || book.getPublisher().contains(publisher))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredBooks);
    }
    @PostMapping("/authors")
    public ResponseEntity<Author> addAuthor(@RequestBody Author newAuthor) {
        newAuthor.setId(authorIdGenerator.getAndIncrement());
        authors.add(newAuthor);
        authorRepo.add(newAuthor);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAuthor);
    }
    @PutMapping("/authors/{authorId}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long authorId, @RequestBody Author updatedAuthor) {
        Author existingAuthor = authors.stream().filter(author -> author.getId().equals(authorId)).findFirst().orElse(null);
        if (existingAuthor != null) {
            // Update the existing book
            existingAuthor.setName(updatedAuthor.getName());
            existingAuthor.setBiography(updatedAuthor.getBiography());

            return ResponseEntity.ok(existingAuthor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
