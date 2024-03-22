package com.doranco.examspring.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.model.entity.Borrowing;
import com.doranco.examspring.payload.Payload;
import com.doranco.examspring.repository.AuthorRepository;
import com.doranco.examspring.repository.BookRepository;
import com.doranco.examspring.repository.BorrowingRepository;


@RestController
public class ApiController {
	
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final BorrowingRepository borrowingRepository;
	
	

    public ApiController(AuthorRepository authorRepository, BookRepository bookRepository,
			BorrowingRepository borrowingRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.borrowingRepository = borrowingRepository;
	}

    //1. Ajouter un livre
	@PostMapping("/books")
    public ResponseEntity<Payload> addBook(@RequestBody Book book)
    {
        Payload payload = new Payload();
        book = bookRepository.save(book);
        payload.setContent(book);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }
	
	//2. Modifier un livre
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Payload> updateBook(@RequestBody Book formBook, @PathVariable int id)
    {
        Payload payload = new Payload();
        try {
            var book = bookRepository.findById(id).get();
            book.setTitle(formBook.getTitle());
            book.setAuthor(formBook.getAuthor());
            book.setPublisher(formBook.getPublisher());
            book.setPublicationYear(formBook.getPublicationYear());
            book.setPageCount(formBook.getPageCount());
            var bookUpdated = bookRepository.save(book);

            payload.setContent(bookUpdated);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }
    
    //3. Supprimer un livre
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Payload> deleteBook(@PathVariable int id) {
    	bookRepository.deleteById(id);
        return new ResponseEntity<>(new Payload("Book with ID : " + id + " deleted"), HttpStatus.NO_CONTENT);
    }
    
    //4. Rechercher des livres par titre, auteur ou éditeur
    @GetMapping("/books")
    public List<Book> searchBooksByTitleAuthorOrPublisher(@RequestParam String title, @RequestParam String author, @RequestParam String publisher)
    {
    	return bookRepository.findByTitleContainingOrAuthorContainingOrPublisherContaining(title, author, publisher);
    }
    
    //5. Ajouter un auteur
	@PostMapping("/authors")
    public ResponseEntity<Payload> addAuthor(@RequestBody Author author)
    {
        Payload payload = new Payload();
        author = authorRepository.save(author);
        payload.setContent(author);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }
	
	//6. Modifier un auteur
    @PutMapping("/authors/{authorId}")
    public ResponseEntity<Payload> updateAuthor(@RequestBody Author formAuthor, @PathVariable int id)
    {
        Payload payload = new Payload();
        try {
            var author = authorRepository.findById(id).get();
            author.setName(formAuthor.getName());
            author.setBiography(formAuthor.getBiography());
            var authorUpdated = authorRepository.save(author);

            payload.setContent(authorUpdated);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }
    
    //7. Supprimer un auteur
    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Payload> deleteAuthor(@PathVariable int id) {
    	authorRepository.deleteById(id);
        return new ResponseEntity<>(new Payload("Author with ID : " + id + " deleted"), HttpStatus.NO_CONTENT);
    }
    
    //8. Rechercher auteur par nom
    @GetMapping("/authors")
    public List<Author> searchAuthorsByName(@RequestParam String name)
    {
    	return authorRepository.findByNameContaining(name);
    }
    
    //9. Ajouter un emprunt
	@PostMapping("/borrowings")
    public ResponseEntity<Payload> addBorrowing(@RequestBody Borrowing borrowing)
    {
        Payload payload = new Payload();
        borrowing = borrowingRepository.save(borrowing);
        payload.setContent(borrowing);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }
    
    //10. Modifier un emprunt 
    @PutMapping("/borrowings/{borrowingsId}")
    public ResponseEntity<Payload> updateBorrowing(@RequestBody Borrowing formBorrowing, @PathVariable int id)
    {
        Payload payload = new Payload();
        try {
            var borrowing = borrowingRepository.findById(id).get();
            borrowing.setBookId(formBorrowing.getBookId());
            borrowing.setBorrowDate(formBorrowing.getBorrowDate());
            borrowing.setDueDate(formBorrowing.getDueDate());
            var borrowingUpdated = borrowingRepository.save(borrowing);

            payload.setContent(borrowingUpdated);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }
    
    //11. Supprimer un emprunt
    @DeleteMapping("/borrowings/{id}")
    public ResponseEntity<Payload> deleteBorrowing(@PathVariable int id) {
    	borrowingRepository.deleteById(id);
        return new ResponseEntity<>(new Payload("Borrowing with ID : " + id + " deleted"), HttpStatus.NO_CONTENT);
    }
    
    //12. Rechercher des emprunts en cours ou passés
    @GetMapping("/borrowings")
    public List<Borrowing> searchBorrowingsByStatus(@RequestParam String status)
    {
    	if(status.equalsIgnoreCase("active")) {
    		return borrowingRepository.findAllWithDueDateAfter(LocalDate.now());
    	} else if(status.equalsIgnoreCase("inactive")) {
    		return borrowingRepository.findAllWithDueDateBefore(LocalDate.now());
    	}
    	return null;
    }
    
    //TEST
}
