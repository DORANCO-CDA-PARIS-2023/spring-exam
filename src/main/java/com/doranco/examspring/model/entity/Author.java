package com.doranco.examspring.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Author {

    @Getter
    @Setter
    @Id
    private Long id;
    String name;
    String biography;

    public Author(String name, String biography) {

    }

}
