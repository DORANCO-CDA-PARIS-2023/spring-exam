package com.doranco.examspring.model.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table
public class Borrowing {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private int ind;
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

	public int getInd() {
		return ind;
	}

	public void setInd(int ind) {
		this.ind = ind;
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
		return "Borrowing [ind=" + ind + ", bookId=" + bookId + ", borrowDate=" + borrowDate + ", dueDate=" + dueDate
				+ "]";
	}
    
    

}
