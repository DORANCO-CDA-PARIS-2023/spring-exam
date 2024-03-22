package com.doranco.examspring.repository;

import com.doranco.examspring.model.entity.Author;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    public List<Author> findByName(String name);
}