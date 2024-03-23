package com.doranco.examspring.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.doranco.examspring.controller.api.payload.Payload;
import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.repository.IAuthorRepository;

@RestController
@RequestMapping("/authors")
public class ApiAuthorController {

	private final IAuthorRepository authorRepository;

	public ApiAuthorController(IAuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@PostMapping("")
	public ResponseEntity<Payload> create(@RequestBody Author author) {
		Payload payload = new Payload();
		author = authorRepository.save(author);
		if (author == null) {
			payload.setMessage("Author not created");
			return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
		}
		payload.setData(author);
		payload.setMessage("Author created");
		return new ResponseEntity<>(payload, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Payload> update(@RequestBody Author author, @PathVariable Long id) {
		Optional<Author> optionalAuthor = authorRepository.findById(id);
		Payload payload = new Payload();
		if (optionalAuthor == null) {
			payload.setMessage("Author not faond");
			return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
		}
		Author olderAuthor = optionalAuthor.get();
		olderAuthor.setName(author.getName());
		olderAuthor.setBiography(author.getBiography());
		olderAuthor = authorRepository.save(olderAuthor);
		payload.setData(olderAuthor);
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Payload> delete(@PathVariable Long id) {
		Payload payload = new Payload();
		Optional<Author> optionalAuthor = authorRepository.findById(id);
		if (optionalAuthor == null) {
			payload.setMessage("Author not found");
			return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
		} else {
			authorRepository.delete(optionalAuthor.get());
		}
		Author author = optionalAuthor.get();
		authorRepository.delete(author);
		payload.setMessage("Author deleted");
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<Payload> search(@RequestParam String keyword) {
		Payload payload = new Payload();
		List<Author> authors = authorRepository.findByName(keyword);
		if (authors == null) {
			payload.setMessage("Author not found");
			return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
		}
		payload.setData(authors);
		payload.setMessage("Author found");
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}

}
