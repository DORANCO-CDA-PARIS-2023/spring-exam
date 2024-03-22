package com.doranco.examspring.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private int id;
	private String title;
	private String author;
	private String publisher;
	private int publicationYear;
	private int pageCount;
	
    public Book() {}

    public Book(String title, String author, String publisher, int publicationYear, int pageCount) {
    	this.title = title;
    	this.author = author;
    	this.publisher = publisher;
    	this.pageCount = pageCount;
    	this.publicationYear = publicationYear;
    	this.pageCount = pageCount;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", publicationYear=" + publicationYear + ", pageCount=" + pageCount + "]";
	}
    
    
}
