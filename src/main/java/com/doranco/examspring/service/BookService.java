package com.doranco.examspring.service;

import com.doranco.examspring.model.entity.Book;

import java.util.List;

public interface BookService {

    Book create(Book book);

    List<Book> read();

    Book update(Long id, Book book);

    String delete(Long id);
}
