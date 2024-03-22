package com.doranco.examspring.repository;

import com.doranco.examspring.model.entity.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    public List<Book> findByTitle(String title);

    public List<Book> findByAuthor(String author);

    public List<Book> findByPublisher(String publisher);
}
