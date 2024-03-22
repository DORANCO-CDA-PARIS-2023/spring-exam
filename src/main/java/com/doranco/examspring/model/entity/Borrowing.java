package com.doranco.examspring.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrowing")
public class Borrowing {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_id", length = 100, nullable = false)
    private Long bookId;
    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    public Borrowing() {

    }

    public Borrowing(Long bookId, LocalDate borrowDate, LocalDate dueDate) {
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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
                ", book='" + bookId + '\'' +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
