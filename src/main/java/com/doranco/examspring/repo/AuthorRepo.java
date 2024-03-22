package com.doranco.examspring.repo;

import com.doranco.examspring.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {
    default void add(Author author){
        save(author);
    }

    default void delete(Author author) {
        delete(author);
    }

}
