package com.doranco.examspring.controller;

import com.doranco.examspring.controller.payload.Payload;
import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.model.entity.Borrowing;
import com.doranco.examspring.model.repository.AuthorRepository;
import com.doranco.examspring.model.repository.BookRepository;
import com.doranco.examspring.model.repository.BorrowingRepository;
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

    public ApiController(BookRepository bookRepository, AuthorRepository authorRepository,
                         BorrowingRepository borrowingRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.borrowingRepository = borrowingRepository;
    }

    @PostMapping("/books")
    public ResponseEntity<Payload> addBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(new Payload("Livre ajouté avec succès", savedBook), HttpStatus.CREATED);
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
            Book updatedBook = bookRepository.save(existingBook);
            return new ResponseEntity<>(new Payload("Livre mis à jour avec succès", updatedBook), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Payload("Livre non trouvé"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Payload> deleteBook(@PathVariable Long bookId) {
        bookRepository.deleteById(bookId);
        return new ResponseEntity<>(new Payload("Livre supprimé avec succès"), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/books")
    public ResponseEntity<Payload> searchBooks(@RequestParam(required = false) String title,
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
        if (!books.isEmpty()) {
            return new ResponseEntity<>(new Payload("Livres trouvés", books), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Payload("Aucun livre trouvé"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/authors")
    public ResponseEntity<Payload> addAuthor(@RequestBody Author author) {
        Author savedAuthor = authorRepository.save(author);
        return new ResponseEntity<>(new Payload("Auteur ajouté avec succès", savedAuthor), HttpStatus.CREATED);
    }

    @PutMapping("/authors/{authorId}")
    public ResponseEntity<Payload> updateAuthor(@PathVariable Long authorId, @RequestBody Author author) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()) {
            Author existingAuthor = optionalAuthor.get();
            existingAuthor.setName(author.getName());
            existingAuthor.setBiography(author.getBiography());
            Author updatedAuthor = authorRepository.save(existingAuthor);
            return new ResponseEntity<>(new Payload("Auteur mis à jour avec succès", updatedAuthor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Payload("Auteur non trouvé"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/authors/{authorId}")
    public ResponseEntity<Payload> deleteAuthor(@PathVariable Long authorId) {
        authorRepository.deleteById(authorId);
        return new ResponseEntity<>(new Payload("Auteur supprimé avec succès"), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/authors")
    public ResponseEntity<Payload> searchAuthors(@RequestParam(required = false) String name) {
        List<Author> authors;
        if (name != null) {
            authors = authorRepository.findByNameContaining(name);
        } else {
            authors = authorRepository.findAll();
        }
        if (!authors.isEmpty()) {
            return new ResponseEntity<>(new Payload("Auteurs trouvés", authors), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Payload("Aucun auteur trouvé"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/borrowings")
    public ResponseEntity<Payload> addBorrowing(@RequestBody Borrowing borrowing) {
        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        return new ResponseEntity<>(new Payload("Emprunt ajouté avec succès", savedBorrowing), HttpStatus.CREATED);
    }

    @PutMapping("/borrowings/{borrowingId}")
    public ResponseEntity<Payload> updateBorrowing(@PathVariable Long borrowingId, @RequestBody Borrowing borrowing) {
        Optional<Borrowing> optionalBorrowing = borrowingRepository.findById(borrowingId);
        if (optionalBorrowing.isPresent()) {
            Borrowing existingBorrowing = optionalBorrowing.get();
            existingBorrowing.setBookId(borrowing.getBookId());
            existingBorrowing.setBorrowDate(borrowing.getBorrowDate());
            existingBorrowing.setDueDate(borrowing.getDueDate());
            Borrowing updatedBorrowing = borrowingRepository.save(existingBorrowing);
            return new ResponseEntity<>(new Payload("Emprunt mis à jour avec succès", updatedBorrowing), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Payload("Emprunt non trouvé"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/borrowings/{borrowingId}")
    public ResponseEntity<Payload> deleteBorrowing(@PathVariable Long borrowingId) {
        borrowingRepository.deleteById(borrowingId);
        return new ResponseEntity<>(new Payload("Emprunt supprimé avec succès"), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/borrowings")
    public ResponseEntity<Payload> searchBorrowings(@RequestParam(required = false) String status) {
        return new ResponseEntity<>(new Payload("Recherche des emprunts"), HttpStatus.OK);
    }
}