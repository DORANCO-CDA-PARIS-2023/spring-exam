package com.doranco.examspring.model.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "borrowing")
public class Borrowing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToMany
	@JoinTable(name = "borrowing_book", joinColumns = @JoinColumn(name = "borrowing_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
	private List<Book> books;

	@Column(name = "borrow_date")
	private LocalDate borrowDate;

	@Column(name = "due_date")
	private LocalDate dueDate;

	private boolean returned;

	public Borrowing() {
		this.borrowDate = LocalDate.now();
		this.returned = false;
	}

	public Borrowing(List<Book> books, LocalDate dueDate) {
		this();
		this.books = books;
		this.dueDate = dueDate;
	}

	public Long getId() {
		return id;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
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

	public boolean isReturned() {
		return returned;
	}

	@Override
	public String toString() {
		return "Borrowing{" + "id=" + id + ", books=" + books + ", borrowDate=" + borrowDate + ", dueDate=" + dueDate
				+ '}';
	}
}
