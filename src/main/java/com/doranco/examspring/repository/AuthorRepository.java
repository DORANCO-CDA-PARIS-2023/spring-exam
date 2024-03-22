package com.doranco.examspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.doranco.examspring.model.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	@Query("SELECT a FROM Author a WHERE a.name LIKE %:name%")
    public List<Author> searchByName(@Param("name") String name);
}
