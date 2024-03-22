package com.doranco.examspring.repository;

import com.doranco.examspring.model.entity.Borrowing;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    
    @Query("SELECT b FROM Borrowing b WHERE b.dueDate >= CURRENT_DATE AND b.returnDate IS NULL")
    public List<Borrowing> findBorrowingsInProgress();

    @Query("SELECT b FROM Borrowing b WHERE b.returnDate IS NOT NULL")
    public List<Borrowing> findCompletedBorrowings();
}
