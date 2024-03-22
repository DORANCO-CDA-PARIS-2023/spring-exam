package com.doranco.examspring.repository;

import com.doranco.examspring.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingOrAuthorContainingOrPublisherContaining(String title, String author, String publisher);

}
