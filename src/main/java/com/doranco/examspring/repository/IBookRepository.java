package com.doranco.examspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Add this import statement
import com.doranco.examspring.model.entity.Book; 

@Repository
public interface IBookRepository extends JpaRepository<Book, Integer>{

    List<Book> findAll();      
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByPublisher(String publisher);
    
}
