package com.doranco.examspring.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.doranco.examspring.model.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

    List<Book> findByTitleContaining(String title);

    List<Book> findByAuthorContaining(String author);

    List<Book> findByPublisherContaining(String publisher);
    
}
