package com.doranco.examspring.controller.api;

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
		return new ResponseEntity<>(payload, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Payload> update(@RequestBody Author author, @PathVariable Long id) {
		Payload payload = new Payload();
		Author olderAuthor = authorRepository.findById(id).orElse(null);
		if (olderAuthor == null) {
			payload.setMessage("Author not faond");
			return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
		}
		olderAuthor.setName(author.getName());
		olderAuthor.setBiography(author.getBiography());
		payload.setData(olderAuthor);
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Payload> delete(@PathVariable Long id) {
		Payload payload = new Payload();
		Author author = authorRepository.findById(id).orElse(null);
		if (author == null) {
			payload.setMessage("Author not found");
			return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
		}
		authorRepository.delete(author);
		payload.setMessage("Author deleted");
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}
	
	
	@GetMapping("/search")
	public ResponseEntity<Payload> search(@RequestParam String name) {
		Payload payload = new Payload();
		Author author = (Author) authorRepository.findByName(name);
		if (author == null) {
			payload.setMessage("Author not found");
			return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
		}
		payload.setData(author);
		
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}

}
