package com.doranco.examspring.repository;

import com.doranco.examspring.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByPublisher(String publisher);
    List<Book> findByTitleAndAuthorAndPublisher(String title, String author, String publisher);
    List<Book> findByTitleAndAuthor(String title, String author);

    List<Book> findByTitleAndPublisher(String title, String publisher);

    List<Book> findByAuthorAndPublisher(String author, String publisher);


}
