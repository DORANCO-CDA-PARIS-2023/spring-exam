package com.doranco.examspring.model.repository;

import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAllByNameContainingIgnoreCase(String name);

}
