package com.doranco.examspring.controller.api;

import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.model.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthorApiController {

    private final AuthorRepository authorRepository;

    public AuthorApiController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostMapping("/authors")
    public ResponseEntity<Payload> addAuthor(@RequestBody Author author) {
        Payload payload = new Payload();
        Author savedAuthor = authorRepository.save(author);
        payload.setMessage("Author saved.");
        payload.setData(savedAuthor);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }

    @PutMapping("/authors/{authorId}")
    public ResponseEntity<Payload> updateAuthor(@PathVariable Long authorId, @RequestBody Author author) {
        Payload payload = new Payload();

        Optional<Author> authorById = authorRepository.findById(authorId);
        if (authorById.isEmpty()) {
            // UTILISER NO_CONTENT CAR CE N'EST PAS UNE ERREUR
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }

        authorById.get().setName(author.getName());
        authorById.get().setBiography(author.getBiography());

        payload.setMessage("Author updated.");
        payload.setData(authorById.get());
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @DeleteMapping("/authors/{authorId}")
    public ResponseEntity<Payload> deleteAuthor(@PathVariable Long authorId) {
        Payload payload = new Payload();

        Optional<Author> authorById = authorRepository.findById(authorId);
        if (authorById.isEmpty()) {
            // UTILISER NO_CONTENT CAR CE N'EST PAS UNE ERREUR
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }

        authorRepository.delete(authorById.get());
        payload.setMessage("Author deleted.");
        return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/authors")
    public ResponseEntity<Payload> getAuthorByName(@RequestParam String name) {
        Payload payload = new Payload();
        List<Author> authors = authorRepository.findAllByNameContainingIgnoreCase(name);

        if (authors.isEmpty()) {
            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        }

        payload.setMessage(String.format(
                "API found %d %s.",
                authors.size(),
                authors.size() > 1 ? "results" : "result"
        ));
        payload.setData(authors);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
}
