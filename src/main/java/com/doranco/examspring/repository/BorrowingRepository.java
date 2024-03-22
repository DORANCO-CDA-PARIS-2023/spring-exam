package com.doranco.examspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doranco.examspring.model.entity.Borrowing;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Integer> {

}
