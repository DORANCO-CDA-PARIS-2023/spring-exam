package com.doranco.examspring.controller;

import org.springframework.web.bind.annotation.RestController;
import com.doranco.examspring.controller.payload.Payload;
import com.doranco.examspring.entity.Books;
import com.doranco.examspring.repository.BooksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final BooksRepository booksRepository;
    
    public ApiController(BooksRepository booksRepository)
    {
        this.booksRepository = booksRepository;
    }

    @PostMapping("/books")
    public ResponseEntity<Payload> addBooks(@RequestBody Books books)
    {
        var data = this.booksRepository.save(books);
        var payload = new Payload("Add new books", data);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }


    @GetMapping("/books")
    public ResponseEntity<Payload> getAllBooks()
    {
        return new ResponseEntity<>(
                new Payload("Get All books", booksRepository.findAll()),
                HttpStatus.OK
        );
    }

    @GetMapping("/books/search")
    public ResponseEntity<Payload> searchBooks(@RequestParam String email, @RequestParam String phone)
    {
        System.out.println("titre:" + titre);
        Payload payload = new Payload();
        var data = booksRepository.findByTitle(titre);
        System.out.println(data);
        if (data.isEmpty()) {
            payload.setMessage("Books Not found ...");
            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        }
        payload.setMessage("Get All books by title");
        payload.setData(data);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Payload> deleteBooks(@PathVariable Long id)
    {
        booksRepository.deleteById(id);
        return new ResponseEntity<>(new Payload("Books deleted"), HttpStatus.NO_CONTENT);
    }
}


}
