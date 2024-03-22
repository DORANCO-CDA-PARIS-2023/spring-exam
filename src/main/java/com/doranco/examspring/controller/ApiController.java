package com.doranco.examspring.controller;

import com.doranco.examspring.controller.payload.Payload;
import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class ApiController {

    private final BookRepository bookRepository;

    public ApiController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @PostMapping("/books")
    public ResponseEntity<Payload> addBook(@RequestBody Book book){
        Payload payload = new Payload();
        book = bookRepository.save(book);
        payload.setMessage("Book added");
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }


}
