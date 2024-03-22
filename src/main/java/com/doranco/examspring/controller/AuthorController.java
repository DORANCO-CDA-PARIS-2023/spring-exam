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

import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.repository.AuthorRepository;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    
    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Author savedAuthor = authorRepository.save(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    
    @PutMapping("/{authorId}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long authorId, @RequestBody Author author) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (!optionalAuthor.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Author existingAuthor = optionalAuthor.get();
        existingAuthor.setName(author.getName());
        existingAuthor.setBiography(author.getBiography());

        Author updatedAuthor = authorRepository.save(existingAuthor);
        return ResponseEntity.ok(updatedAuthor);
    }

    
    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long authorId) {
        authorRepository.deleteById(authorId);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping(params = "name")
    public ResponseEntity<List<Author>> findAuthorsByName(@RequestParam String name) {
        List<Author> authors = authorRepository.findByNameContaining(name);
        return ResponseEntity.ok(authors);
    }
}
