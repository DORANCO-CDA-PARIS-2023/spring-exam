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

import com.doranco.examspring.model.entity.Borrowing;
import com.doranco.examspring.repository.BorrowingRepository;

@RestController
@RequestMapping("/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingRepository borrowingRepository;

    
    @PostMapping
    public ResponseEntity<Borrowing> addBorrowing(@RequestBody Borrowing borrowing) {
        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        return new ResponseEntity<>(savedBorrowing, HttpStatus.CREATED);
    }

    
    @PutMapping("/{borrowingId}")
    public ResponseEntity<Borrowing> updateBorrowing(@PathVariable Long borrowingId, @RequestBody Borrowing borrowing) {
        Optional<Borrowing> optionalBorrowing = borrowingRepository.findById(borrowingId);
        if (!optionalBorrowing.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Borrowing existingBorrowing = optionalBorrowing.get();
        existingBorrowing.setBook(borrowing.getBook());
        existingBorrowing.setBorrowDate(borrowing.getBorrowDate());
        existingBorrowing.setDueDate(borrowing.getDueDate());

        Borrowing updatedBorrowing = borrowingRepository.save(existingBorrowing);
        return ResponseEntity.ok(updatedBorrowing);
    }

    
    @DeleteMapping("/{borrowingId}")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable Long borrowingId) {
        borrowingRepository.deleteById(borrowingId);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping(params = "status")
    public ResponseEntity<List<Borrowing>> findBorrowingsByStatus(@RequestParam String status) {
        List<Borrowing> borrowings;
        if (status.equalsIgnoreCase("in-progress")) {
            borrowings = borrowingRepository.findByDueDateAfterAndReturnDateIsNull(null);
        } else if (status.equalsIgnoreCase("completed")) {
            borrowings = borrowingRepository.findByReturnDateIsNotNull();
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(borrowings);
    }
}
