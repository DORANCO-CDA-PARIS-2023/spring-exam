package com.doranco.examspring.controller.api;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.doranco.examspring.controller.api.payload.Payload;
import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.model.entity.Borrowing;
import com.doranco.examspring.repository.IBookRepository;
import com.doranco.examspring.repository.IBorrowingRepository;

@RestController
@RequestMapping("/borrowings")
public class ApiBorrowingController {

	private final IBorrowingRepository borrowingRepository;
	private final IBookRepository bookRepository;

	public ApiBorrowingController(IBorrowingRepository borrowingRepository, IBookRepository bookRepository) {
		this.borrowingRepository = borrowingRepository;
		this.bookRepository = bookRepository;
	}

	@PostMapping("")
	public ResponseEntity<Payload> create(@RequestBody BorrowingRequest borrowingRequest) {
		Payload payload = new Payload();

		Book book = bookRepository.findById(borrowingRequest.getBookId()).orElse(null);
		if (book == null) {
			payload.setMessage("Book not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(payload);
		}

		Borrowing borrowing = new Borrowing();
		borrowing.setBooks(Collections.singletonList(book));
		borrowing.setDueDate(borrowingRequest.getDueDate());

		borrowing = borrowingRepository.save(borrowing);
		if (borrowing == null) {
			payload.setMessage("Borrowing not created");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payload);
		}

		payload.setData(borrowing);
		payload.setMessage("Borrowing created");
		return ResponseEntity.status(HttpStatus.CREATED).body(payload);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Payload> updateBorrowing(@RequestBody Borrowing borrowing, @PathVariable Long id) {
	    Payload payload = new Payload();
	    Optional<Borrowing> optionalBorrowing = borrowingRepository.findById(id);
	    if (optionalBorrowing.isEmpty()) {
	        payload.setMessage("Borrowing not found");
	        return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
	    }
	    Borrowing olderBorrowing = optionalBorrowing.get();
	    olderBorrowing.setBooks(borrowing.getBooks());
	    olderBorrowing.setBorrowDate(borrowing.getBorrowDate());
	    olderBorrowing.setDueDate(borrowing.getDueDate());
	    borrowingRepository.save(olderBorrowing);
	    payload.setData(olderBorrowing);
	    payload.setMessage("Borrowing updated");
	    return new ResponseEntity<>(payload, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Payload> deleteBorrowing(@PathVariable Long id) {
	    Payload payload = new Payload();
	    Optional<Borrowing> optionalBorrowing = borrowingRepository.findById(id);
	    if (optionalBorrowing.isEmpty()) {
	        payload.setMessage("Borrowing not found");
	        return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
	    }
	    Borrowing borrowing = optionalBorrowing.get();
	    borrowingRepository.delete(borrowing);
	    payload.setMessage("Borrowing deleted");
	    return new ResponseEntity<>(payload, HttpStatus.OK);
	}


	// http://localhost:8080/borrowings/search?status=overdue
	// http://localhost:8080/borrowings/search?status=notReturned
	@GetMapping("/search")
	public ResponseEntity<Payload> searchBorrowings(@RequestParam String status) {
		Payload payload = new Payload();
		List<Borrowing> borrowings;

		if ("overdue".equalsIgnoreCase(status)) {
			borrowings = borrowingRepository.getBookDueDateDepasse();
		} else if ("notReturned".equalsIgnoreCase(status)) {
			borrowings = borrowingRepository.getBookNotReturned();
		} else {
			return ResponseEntity.badRequest().body(new Payload("Invalid status"));
		}

		if (borrowings.isEmpty()) {
			payload.setMessage("No borrowings found");
			return ResponseEntity.notFound().build();
		}

		payload.setData(borrowings);
		payload.setMessage("Borrowings found");
		return ResponseEntity.ok(payload);
	}

}
