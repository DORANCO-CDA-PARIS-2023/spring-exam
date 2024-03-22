package com.doranco.examspring.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author", length = 60, nullable = false)
    private String author;
    @Column(name = "publisher", length = 60, nullable = false)
    private String publisher;
    @Column(name = "publication_year", nullable = false)
    private short publicationYear;
    @Column(name = "page_count", nullable = false)
    private short pageCount;

    public Book() {

    }

    public Book(String title, String author, String publisher, short publicationYear, short pageCount, Borrowing borrowing) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.pageCount = pageCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public short getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(short publicationYear) {
        this.publicationYear = publicationYear;
    }

    public short getPageCount() {
        return pageCount;
    }

    public void setPageCount(short pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", publisher='" + publisher + '\'' +
                ", publicationYear=" + publicationYear +
                ", pageCount=" + pageCount +
                '}';
    }

}
