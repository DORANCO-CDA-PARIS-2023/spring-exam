package com.doranco.examspring.repository;

import com.doranco.examspring.model.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> { }
