package com.doranco.examspring.repo;

import com.doranco.examspring.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {


default void add(Book book){
    save(book);
}

default void delete(Book book) {
    delete(book);
}

}
