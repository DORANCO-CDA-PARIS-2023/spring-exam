package com.doranco.examspring.model.repository;

import com.doranco.examspring.model.entity.Borrowing;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
	
	@Query("SELECT b FROM Borrowing b WHERE b.dueDate > CURRENT_DATE")
    List<Borrowing> findByDueDateAfterNow();

    @Query("SELECT b FROM Borrowing b WHERE b.dueDate < CURRENT_DATE")
    List<Borrowing> findByDueDateBeforeNow();
}
