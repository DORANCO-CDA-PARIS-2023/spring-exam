package com.doranco.examspring.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doranco.examspring.model.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

    List<Author> findByNameContaining(String name);
    
}
