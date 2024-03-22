package com.doranco.examspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doranco.examspring.model.entity.Author;

public interface IAuthorRepository extends JpaRepository<Author, Long> {

	Object findByName(String name);

}
