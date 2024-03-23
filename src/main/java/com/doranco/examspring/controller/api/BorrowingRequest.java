package com.doranco.examspring.controller.api;

import java.time.LocalDate;

public class BorrowingRequest {
	
	private Long bookId;
    private LocalDate dueDate;
    
    public BorrowingRequest() {
    	
    }
    
	public BorrowingRequest(Long bookId, LocalDate dueDate) {
		this.bookId = bookId;
		this.dueDate = dueDate;
	}
	
	
	public Long getBookId() {
		return bookId;
	}
	
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "BorrowingRequest [bookId=" + bookId + ", dueDate=" + dueDate + "]";
	}
	
	

}
