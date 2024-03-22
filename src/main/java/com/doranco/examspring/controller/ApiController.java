package com.doranco.examspring.controller;

import com.doranco.examspring.controller.payload.Payload;
import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.repository.AuthorRepository;
import com.doranco.examspring.repository.BookRepository;
import com.doranco.examspring.repository.BorrowingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BorrowingRepository borrowingRepository;

    public ApiController(BookRepository bookRepository, AuthorRepository authorRepository, BorrowingRepository borrowingRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.borrowingRepository = borrowingRepository;
    }

    @PostMapping("/books")
    public ResponseEntity<Payload> addBook(@RequestBody Book book)
    {
        var savedBook = this.bookRepository.save(book);
        var payload = new Payload("Nouveau Livre ajouté", savedBook);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Payload> updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPublisher(updatedBook.getPublisher());
            existingBook.setPublicationYear(updatedBook.getPublicationYear());
            existingBook.setPageCount(updatedBook.getPageCount());
            Book savedBook = bookRepository.save(existingBook);
            var payload = new Payload("Livre mis à jour", savedBook);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Payload("Livre non trouvé"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Payload> deleteBook(@PathVariable Long bookId){
        bookRepository.deleteById(bookId);
        return new ResponseEntity<>(new Payload("Livre supprimé"), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/books?title={title}&author={author}&publisher={publisher}")
    public ResponseEntity<List<Book>> findBooksByCriteria(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher) {

        List<Book> books;

        if (title != null && author != null && publisher != null) {
            books = bookRepository.findByTitleAndAuthorAndPublisher(title, author, publisher);
        } else if (title != null && author != null) {
            books = bookRepository.findByTitleAndAuthor(title, author);
        } else if (title != null && publisher != null) {
            books = bookRepository.findByTitleAndPublisher(title, publisher);
        } else if (author != null && publisher != null) {
            books = bookRepository.findByAuthorAndPublisher(author, publisher);
        } else if (title != null) {
            books = bookRepository.findByTitle(title);
        } else if (author != null) {
            books = bookRepository.findByAuthor(author);
        } else if (publisher != null) {
            books = bookRepository.findByPublisher(publisher);
        } else {
            books = bookRepository.findAll();
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/authors")
    public ResponseEntity<Payload> addAuthor(@RequestBody Author author) {
        Author savedAuthor = authorRepository.save(author);
        var payload = new Payload("Nouvel auteur ajouté", savedAuthor);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }

    @PutMapping("/authors/{authorId}")
    public ResponseEntity<Payload> updateAuthor(@PathVariable Long authorId, @RequestBody Author updatedAuthor) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()) {
            Author existingAuthor = optionalAuthor.get();
            existingAuthor.setName(updatedAuthor.getName());
            existingAuthor.setBiography(updatedAuthor.getBiography());
            Author savedAuthor = authorRepository.save(existingAuthor);
            var payload = new Payload("Informations de l'auteur mises à jour", savedAuthor);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Payload("Auteur non trouvé"), HttpStatus.NOT_FOUND);
        }
    }

}
