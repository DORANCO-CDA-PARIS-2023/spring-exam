package com.doranco.examspring.controller.api;

import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.model.entity.Borrowing;
import com.doranco.examspring.model.repository.BorrowingRepository;
import jakarta.transaction.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BorrowingApiController {

    private final BorrowingRepository borrowingRepository;

    public BorrowingApiController(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    @PostMapping("/borrowings")
    public ResponseEntity<Payload> addBorrowing(@RequestBody Borrowing borrowing) {
        Payload payload = new Payload();
        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        payload.setMessage("Borrowing saved.");
        payload.setData(savedBorrowing);
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }

    @PutMapping("/borrowings/{borrowingId}")
    public ResponseEntity<Payload> updateBorrowing(@PathVariable Long borrowingId, @RequestBody Borrowing borrowing) {
        Payload payload = new Payload();

        Optional<Borrowing> borrowingById = borrowingRepository.findById(borrowingId);
        if (borrowingById.isEmpty()) {
            // UTILISER NO_CONTENT CAR CE N'EST PAS UNE ERREUR
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }

        borrowingById.get().setBorrowDate(borrowing.getBorrowDate());
        borrowingById.get().setDueDate(borrowing.getDueDate());
        borrowingById.get().setBookId(borrowing.getBookId());

        payload.setMessage("Borrowing updated.");
        payload.setData(borrowingById.get());
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @DeleteMapping("/borrowings/{borrowingId}")
    public ResponseEntity<Payload> deleteBorrowing(@PathVariable Long borrowingId) {
        Payload payload = new Payload();

        Optional<Borrowing> borrowingById = borrowingRepository.findById(borrowingId);
        if (borrowingById.isEmpty()) {
            // UTILISER NO_CONTENT CAR CE N'EST PAS UNE ERREUR
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }

        borrowingRepository.delete(borrowingById.get());
        payload.setMessage("Borrowing deleted.");
        // UTILISER OK CAR LA REQUETE EST UN SUCCES
        return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/borrowings")
    public ResponseEntity<Payload> getBorrowingByStatus(@RequestParam BorrowingStatus status) {
        Payload payload = new Payload();

        List<Borrowing> borrowings = new ArrayList<>();

        switch (status) {
            case PAST -> {
                borrowings = borrowingRepository.findAllByDueDateIsLessThan(LocalDate.now());
            }
            case ONGOING -> {
                borrowings = borrowingRepository.findAllByDueDateIsGreaterThanEqual(LocalDate.now());
            }
        }

        if (borrowings.isEmpty()) {
            // UTILISER NO_CONTENT CAR CE N'EST PAS UNE ERREUR
            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        }

        payload.setMessage(String.format(
                "API found %d %s.",
                borrowings.size(),
                borrowings.size() > 1 ? "results" : "result"
        ));
        payload.setData(borrowings);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

}
