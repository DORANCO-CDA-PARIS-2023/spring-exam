package com.doranco.examspring.model.repository;

import com.doranco.examspring.model.entity.Author;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByName(String name);

}