package com.doranco.examspring.service;

import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class BookServiceImplement implements BookService{

    public BookServiceImplement() {
        bookRepository = null;
    }
    
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImplement(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> read() {
        return bookRepository.findAll();
    }

    @Override
    public Book update(Long id, Book book) {
        return bookRepository.findById(id)
                .map(p-> {
                    p.setTitle(book.getTitle());
                    p.setAuthor(book.getAuthor());
                    p.setPublisher(book.getPublisher());
                    p.setPublicationYear(book.getPublicationYear());
                    p.setPageCount(book.getPageCount());
                    return bookRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Livre non trouvé !"));
    }

    @Override
    public String delete(Long id) {
        return null;
    }
}
