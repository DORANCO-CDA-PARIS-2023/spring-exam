package com.doranco.examspring.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Book {

    @Getter
    @Setter
    @Id
    private Long id;
    String author;
    String publisher;
    String title;
    int publicationYear;
    int pageCount;

    public Book(String title, String author, String publisher, int publicationYear, int pageCount) {

    }

}
