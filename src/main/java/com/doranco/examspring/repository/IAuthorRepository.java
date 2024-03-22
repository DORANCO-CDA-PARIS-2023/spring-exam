package com.doranco.examspring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.doranco.examspring.model.entity.Author;
import java.util.List;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByName(String name);
}
