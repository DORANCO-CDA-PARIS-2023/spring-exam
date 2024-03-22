package com.doranco.examspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doranco.examspring.model.entity.Book;


@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

	List<Book> findByTitleOrAuthorOrPublisher(String title, String author, String publisher);

}
