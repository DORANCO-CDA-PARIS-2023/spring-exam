package com.doranco.examspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long authorId, Author author) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (!optionalAuthor.isPresent()) {
           
            return null;
        }
        author.setId(authorId); 
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    public List<Author> findAuthorsByName(String name) {
        return authorRepository.findByNameContaining(name);
    }
}