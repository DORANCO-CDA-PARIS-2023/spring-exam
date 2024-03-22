package com.doranco.examspring.model.repository;

import com.doranco.examspring.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByTitleContainingOrAuthorContainingOrPublisherContaining(String title, String author, String publisher);

}
