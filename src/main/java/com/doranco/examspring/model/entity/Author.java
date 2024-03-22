package com.doranco.examspring.model.entity;

import jakarta.persistence.Entity;

@Entity
public class Author {

    private int id;
    private String name;
    private String biography;

    public Author() {}

    public Author(String name, String biography) {
        this.name = name;
        this.biography = biography;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBiography() {
        return biography;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", biography='" + biography + '\'' +
                '}';
    }
}
