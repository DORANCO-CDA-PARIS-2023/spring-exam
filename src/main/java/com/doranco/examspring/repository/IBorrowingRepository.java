package com.doranco.examspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.model.entity.Borrowing;

public interface IBorrowingRepository extends JpaRepository<Borrowing, Long> {

	List<Book> getBookDueDateDepasse();


}
