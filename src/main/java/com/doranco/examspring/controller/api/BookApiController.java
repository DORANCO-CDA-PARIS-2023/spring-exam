package com.doranco.examspring.controller.api;

import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.model.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookApiController {

    private final BookRepository bookRepository;

    public BookApiController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/books")
    public ResponseEntity<Payload> addBook(@RequestBody Book book) {
        Payload payload = new Payload();
        // Todo: checks
        Book savedBook = bookRepository.save(book);
        payload.setMessage("Book created.");
        payload.setData(savedBook);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Payload> updateBook(@PathVariable Long bookId, @RequestBody Book book) {
        Payload payload = new Payload();

        Optional<Book> bookById = bookRepository.findById(bookId);
        if (bookById.isEmpty()) {
            // UTILISER NO_CONTENT CAR CE N'EST PAS UNE ERREUR
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }

        bookById.get().setTitle(book.getTitle());
        bookById.get().setAuthor(book.getAuthor());
        bookById.get().setPublisher(book.getPublisher());
        bookById.get().setPublicationYear(book.getPublicationYear());
        bookById.get().setPageCount(book.getPageCount());
        bookRepository.save(bookById.get());

        payload.setMessage("Book updated.");
        payload.setData(bookById.get());
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Payload> deleteBook(@PathVariable Long bookId) {
        Payload payload = new Payload();

        Optional<Book> bookById = bookRepository.findById(bookId);
        if (bookById.isEmpty()) {
            // UTILISER NO_CONTENT CAR CE N'EST PAS UNE ERREUR
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }

        bookRepository.delete(bookById.get());
        payload.setMessage("Book deleted.");
        // UTILISER OK CAR LA REQUETE EST UN SUCCES
        return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/books")
    public ResponseEntity<Payload> searchBook(@RequestParam String title, @RequestParam String author, @RequestParam String publisher) {
        Payload payload = new Payload();
        List<Book> books = bookRepository
                .findAllByTitleContainingOrAuthorContainingOrPublisherContaining(title, author, publisher);

        if (books.isEmpty()) {
            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        }

        payload.setMessage(String.format(
                "API found %d %s.",
                books.size(),
                books.size() > 1 ? "results" : "result"
        ));
        payload.setData(books);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

}
