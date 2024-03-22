package com.doranco.examspring.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doranco.examspring.model.entity.Borrowing;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long>{

}
