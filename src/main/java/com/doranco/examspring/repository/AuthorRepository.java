package com.doranco.examspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doranco.examspring.model.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
	
	public List<Author> findByNameContaining(String name);

}
