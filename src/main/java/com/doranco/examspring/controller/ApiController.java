package com.doranco.examspring.controller;

import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.repository.BookRepository;
import com.doranco.examspring.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class ApiController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ApiController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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
    @PostMapping
    public ResponseEntity<String> addAuthor(@RequestBody Author author) {
        Author savedAuthor = authorRepository.save(author);
        if (savedAuthor != null) {
            return new ResponseEntity<>("Auteur ajouté", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Échec dans l'ajout de l'auteur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<String> updateAuthor(@PathVariable Long authorId, @RequestBody Author updatedAuthor) {
        if (!authorRepository.existsById(authorId)) {
            return new ResponseEntity<>("Auteur inexistant", HttpStatus.NOT_FOUND);
        }

        // Vous pouvez ajouter ici la logique pour mettre à jour les informations de l'auteur existant avec les données de l'auteur mis à jour
        // Pour l'instant, nous renvoyons simplement un message indiquant que l'auteur a été mis à jour
        return new ResponseEntity<>("Auteur mis à jour", HttpStatus.OK);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long authorId) {
        if (authorRepository.existsById(authorId)) {
            authorRepository.deleteById(authorId);
            return new ResponseEntity<>("Auteur supprimé", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Auteur inexistant", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<Author>> searchAuthors(@RequestParam(required = false) String name) {
        List<Author> authors;
        if (name != null) {
            authors = authorRepository.findByName(name);
            if (!authors.isEmpty()) {
                return new ResponseEntity<>(authors, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
