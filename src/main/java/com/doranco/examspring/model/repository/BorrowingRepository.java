package com.doranco.examspring.model.repository;

import com.doranco.examspring.model.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    @Query("SELECT b FROM Borrowing b WHERE b.dueDate >= :localDate")
    List<Borrowing> findAllByDueDateIsGreaterThanEqual(LocalDate localDate);
    @Query("SELECT b FROM Borrowing b WHERE b.dueDate < :localDate")
    List<Borrowing> findAllByDueDateIsLessThan(LocalDate localDate);

}
