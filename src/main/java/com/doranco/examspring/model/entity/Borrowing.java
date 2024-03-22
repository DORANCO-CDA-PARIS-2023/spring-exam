package com.doranco.examspring.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Borrowing {

    private int id;
    private int bookId;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Borrowing() {

    }

    public Borrowing(int bookId, LocalDate borrowDate, LocalDate dueDate) {
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Borrowing{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                '}';
    }

}
