package com.doranco.examspring.repo;

import com.doranco.examspring.model.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepo extends JpaRepository<Borrowing,Long> {

}
