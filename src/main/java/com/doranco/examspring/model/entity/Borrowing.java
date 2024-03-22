package com.doranco.examspring.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Borrowing {
    @Id
    private  long id;
    int bookId;
    LocalDate borrowDate;
    LocalDate dueDate;
    public Borrowing(int bookId, LocalDate borrowDate, LocalDate dueDate) {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
