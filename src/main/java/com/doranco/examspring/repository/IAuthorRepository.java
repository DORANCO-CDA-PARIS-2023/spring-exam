package com.doranco.examspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.doranco.examspring.model.entity.Author;

public interface IAuthorRepository extends JpaRepository<Author, Long> {

	@Query("SELECT a FROM Author a WHERE a.name LIKE %:keyword%")
	List<Author> findByName(@Param("keyword") String keyword);

}
