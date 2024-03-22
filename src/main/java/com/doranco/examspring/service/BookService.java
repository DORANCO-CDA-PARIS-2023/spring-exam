package com.doranco.examspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, Book book) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (!optionalBook.isPresent()) {
            
            return null;
        }
        book.setId(bookId); 
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<Book> searchBooks(String title, String author, String publisher) {
        if (title != null) {
            return bookRepository.findByTitleContaining(title);
        } else if (author != null) {
            return bookRepository.findByAuthorContaining(author);
        } else if (publisher != null) {
            return bookRepository.findByPublisherContaining(publisher);
        } else {
            return bookRepository.findAll();
        }
    }
}