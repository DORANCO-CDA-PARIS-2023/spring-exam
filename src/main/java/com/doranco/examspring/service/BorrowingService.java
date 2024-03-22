package com.doranco.examspring.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doranco.examspring.model.entity.Borrowing;
import com.doranco.examspring.repository.BorrowingRepository;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    public Borrowing addBorrowing(Borrowing borrowing) {
        return borrowingRepository.save(borrowing);
    }

    public Borrowing updateBorrowing(Long borrowingId, Borrowing borrowing) {
        Optional<Borrowing> optionalBorrowing = borrowingRepository.findById(borrowingId);
        if (!optionalBorrowing.isPresent()) {
            
            return null;
        }
        borrowing.setId(borrowingId); 
        return borrowingRepository.save(borrowing);
    }

    public void deleteBorrowing(Long borrowingId) {
        borrowingRepository.deleteById(borrowingId);
    }

    public List<Borrowing> findBorrowingsByStatus(String status) {
        if (status.equalsIgnoreCase("in-progress")) {
            return borrowingRepository.findByDueDateAfterAndReturnDateIsNull(LocalDate.now());
        } else if (status.equalsIgnoreCase("completed")) {
            return borrowingRepository.findByReturnDateIsNotNull();
        } else {
            
            return null;
        }
    }
}