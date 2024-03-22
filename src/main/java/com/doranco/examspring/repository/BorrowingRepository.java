package com.doranco.examspring.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doranco.examspring.model.entity.Borrowing;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Integer> {
	
    @Query("select b from Borrowing b where b.dueDate <= :date")
	public List<Borrowing> findAllWithDueDateBefore(LocalDate date);
    
    @Query("select b from Borrowing b where b.dueDate >= :date")
	public List<Borrowing> findAllWithDueDateAfter(LocalDate date);

}
