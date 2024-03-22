package com.doranco.examspring.controller;

import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.model.entity.Borrowing;
import com.doranco.examspring.payload.Payload;
import com.doranco.examspring.repository.IAuthorRepository;
import com.doranco.examspring.repository.IBookRepository;
import com.doranco.examspring.repository.IBorrowingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final IBookRepository iBookRepository;
    private final IAuthorRepository iAuthorRepository;

    private final IBorrowingRepository iBorrowingRepository;

    public ApiController(IBookRepository iBookRepository, IAuthorRepository iAuthorRepository, IBorrowingRepository iBorrowingRepository) {
        this.iBookRepository = iBookRepository;
        this.iAuthorRepository = iAuthorRepository;
        this.iBorrowingRepository = iBorrowingRepository;
    }

    @PostMapping("/books")
    public ResponseEntity<Payload> addBook(@RequestBody Book book) {
        Payload payload = new Payload();
        if (book.getTitle() == null ||book.getAuthor() == null || book.getAuthor().isEmpty() ||
                book.getPublisher() == null || book.getPublisher().isEmpty() ||
                book.getPublicationYear() == 0 || book.getPageCount() == 0
        ) {
            return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
        }
        book = iBookRepository.save(book);
        payload.setMessage("Book created");
        payload.setData(book);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }

    @PutMapping("/books/{bookid}")
    public ResponseEntity<Payload> updateBook(@PathVariable int bookid, @RequestBody Book newBook) {
        Payload payload = new Payload();
        try {
            Book existingBook = iBookRepository.findById(bookid).orElseThrow(() -> new NoSuchElementException("Book not found"));
            if (newBook.getTitle() != null) {
                existingBook.setTitle(newBook.getTitle());
            }
            if (newBook.getAuthor() != null) {
                existingBook.setAuthor(newBook.getAuthor());
            }
            if (newBook.getPublisher() != null) {
                existingBook.setPublisher(newBook.getPublisher());
            }
            if (newBook.getPublicationYear() != 0) {
                existingBook.setPublicationYear(newBook.getPublicationYear());
            }
            if (newBook.getPageCount() != 0) {
                existingBook.setPageCount(newBook.getPageCount());
            }
            existingBook = iBookRepository.save(existingBook);
            payload.setMessage("Book updated");
            payload.setData(existingBook);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            payload.setMessage("Book not found");
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }
@DeleteMapping("/books/{bookid}")
public ResponseEntity<Payload> deleteBook(@PathVariable int bookid) {
    Payload payload = new Payload();
    if (!iBookRepository.existsById(bookid)) {
        payload.setMessage("Book not found");
        return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
    }
    iBookRepository.deleteById(bookid);
    payload.setMessage("Book deleted");
    return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
}
    @GetMapping("/books")
    public ResponseEntity<Payload> getBooks(@RequestParam(required = false) String title,
                                            @RequestParam(required = false) String author,
                                            @RequestParam(required = false) String publisher) {
        Payload payload = new Payload();
        try {
            List<Book> books = iBookRepository.findByTitleOrAuthorOrPublisher(title, author, publisher);
            payload.setMessage("Books retrieved");
            payload.setData(books);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e){
            payload.setMessage("Error occurred while retrieving books");
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/authors")
    public ResponseEntity<Payload> addAuthor(@RequestBody Author author) {
        Payload payload = new Payload();
        if (author.getName() == null ||author.getBiography() == null
        ) {
            return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
        }
        author = iAuthorRepository.save(author);
        payload.setMessage("Author created");
        payload.setData(author);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }

    @PutMapping("/authors/{authorid}")
    public ResponseEntity<Payload> updateAuthor(@PathVariable int authorid, @RequestBody Author newAuthor) {
        Payload payload = new Payload();
        try {
            Author existingauthor = iAuthorRepository.findById(authorid).orElseThrow(() -> new NoSuchElementException("Author not found"));
            if (newAuthor.getName() != null) {
                existingauthor.setName(newAuthor.getName());
            }
            if (newAuthor.getBiography() != null) {
                existingauthor.setBiography(newAuthor.getBiography());
            }

            existingauthor = iAuthorRepository.save(existingauthor);
            payload.setMessage("Author updated");
            payload.setData(existingauthor);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            payload.setMessage("Author not found");
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/authors/{authorid}")
    public ResponseEntity<Payload> deleteAuthor(@PathVariable int authorid) {
        Payload payload = new Payload();
        if (!iAuthorRepository.existsById(authorid)) {
            payload.setMessage("Author not found");
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
        iAuthorRepository.deleteById(authorid);
        payload.setMessage("Book deleted");
        return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/authors/{name}")
    public ResponseEntity<Payload> getAuthorByName(@PathVariable String name) {
        Payload payload = new Payload();
        try {
            Author author = iAuthorRepository.findByName(name);
            payload.setMessage("Author retrieved");
            payload.setData(author);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e){
            payload.setMessage("Author not found");
            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/borrowings")
    public ResponseEntity<Payload> addAuthor(@RequestBody Borrowing borrowing) {
        Payload payload = new Payload();
        if (borrowing.getBook() == null ||borrowing.getBorrowDate() == null || borrowing.getDueDate() ==null
        ) {
            return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
        }
        borrowing = iBorrowingRepository.save(borrowing);
        payload.setMessage("Borrowing created");
        payload.setData(borrowing);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }



}
