package com.doranco.examspring.model.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity @Table
public class Borrowing {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int bookId;
	private LocalDate borrowDate;
	private LocalDate dueDate;

    public Borrowing() {

    }

	public Borrowing(int bookId, LocalDate borrowDate, LocalDate dueDate) {
		this.bookId = bookId;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
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
		return "Borrowing [bookId=" + bookId + ", borrowDate=" + borrowDate + ", dueDate=" + dueDate + "]";
	}
}
