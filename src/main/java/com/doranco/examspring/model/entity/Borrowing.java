package com.doranco.examspring.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity @Table
public class Borrowing {
@Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
@ManyToOne
@JoinColumn(name = "book_id")
private Book book;
private LocalDate borrowDate;
private LocalDate dueDate;
    public Borrowing() {

    }

    public Borrowing(int bookId, LocalDate borrowDate, LocalDate dueDate) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Borrowing{" +
                "id=" + id +
                ", book=" + book +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
