package com.doranco.examspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doranco.examspring.model.entity.Borrowing;

public interface IBorrowingRepository extends JpaRepository<Borrowing, Long> {

	@Query("SELECT b FROM Borrowing b WHERE b.dueDate < CURRENT_DATE")
	List<Borrowing> getBookDueDateDepasse();
	
	
	@Query("SELECT b FROM Borrowing b WHERE b.returned = false")
	List<Borrowing> getBookNotReturned();
}
