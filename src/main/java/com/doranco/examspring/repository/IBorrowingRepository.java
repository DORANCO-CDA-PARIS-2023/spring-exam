package com.doranco.examspring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.doranco.examspring.model.entity.Borrowing;

@Repository
public interface IBorrowingRepository  extends JpaRepository<Borrowing, Integer>{

}
