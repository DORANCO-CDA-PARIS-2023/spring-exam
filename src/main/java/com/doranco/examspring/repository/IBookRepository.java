package com.doranco.examspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.doranco.examspring.model.entity.Book;


@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

//	List<Book> findByTitleOrAuthorOrPublisher(String title, String author, String publisher);
	
	@Query("SELECT b FROM Book b WHERE b.title LIKE %:keyword% OR b.author LIKE %:keyword% OR b.publisher LIKE %:keyword%")
	List<Book> findByTitleOrAuthorOrPublisher(@Param("keyword") String keyword);

}
