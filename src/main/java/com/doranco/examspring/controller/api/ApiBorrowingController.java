package com.doranco.examspring.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.doranco.examspring.controller.api.payload.Payload;
import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.model.entity.Borrowing;
import com.doranco.examspring.repository.IBorrowingRepository;

@RestController
@RequestMapping("/borrowings")
public class ApiBorrowingController {
	
	private final IBorrowingRepository borrowingRepository;
	
	public ApiBorrowingController(IBorrowingRepository borrowingRepository) {
		this.borrowingRepository = borrowingRepository;
	}
	
	@PostMapping("")
	public ResponseEntity<Payload> create(@RequestBody Borrowing borrowing) {
		Payload payload = new Payload();
		borrowing = borrowingRepository.save(borrowing);
		if (borrowing == null) {
			payload.setMessage("Borrowing not created");
			return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
		}
		payload.setData(borrowing);
		return new ResponseEntity<>(payload, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Payload> update(@RequestBody Borrowing borrowing, @PathVariable Long id) {
		Payload payload = new Payload();
		Borrowing olderBorrowing = borrowingRepository.findById(id).orElse(null);
		if (olderBorrowing == null) {
			payload.setMessage("Borrowing not faond");
			return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
		}
		olderBorrowing.setBooks(borrowing.getBooks());
		olderBorrowing.setBorrowDate(borrowing.getBorrowDate());
		olderBorrowing.setDueDate(borrowing.getDueDate());
		payload.setData(olderBorrowing);
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Payload> delete(@PathVariable Long id) {
		Payload payload = new Payload();
		Borrowing borrowing = borrowingRepository.findById(id).orElse(null);
		if (borrowing == null) {
			payload.setMessage("Borrowing not found");
			return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
		}
		borrowingRepository.delete(borrowing);
		payload.setMessage("Borrowing deleted");
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}
	
	
	@GetMapping("/search")
	public ResponseEntity<Payload> search(@RequestParam String title, @RequestParam String author,
			@RequestParam String name) {
		Payload payload = new Payload();
		 List<Book> books = borrowingRepository.getBookDueDateDepasse();
		// Borrowing borrowing = (Borrowing) borrowingRepository.findByName(name);
		// if (borrowing == null) {
		// payload.setMessage("Borrowing not found");
		// return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
		// }
		// payload.setData(borrowing);
		return new ResponseEntity<>(payload, HttpStatus.OK);
	}

}
