package com.doranco.examspring;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.doranco.examspring.model.entity.Author;
import com.doranco.examspring.model.entity.Book;
import com.doranco.examspring.model.entity.Borrowing;
import com.doranco.examspring.repository.AuthorRepository;
import com.doranco.examspring.repository.BookRepository;
import com.doranco.examspring.repository.BorrowingRepository;

@SpringBootApplication
public class ExamSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExamSpringApplication.class, args);
    }
    
	@Bean
	CommandLineRunner start(AuthorRepository authorRepository, BookRepository bookRepository, BorrowingRepository borrowingRepository) {
		return args -> {
			Book livre1 = new Book("Les Robots", "Isaac Asimov", "Gallimard", 1951, 500);
			Book livre2 = new Book("Blade Runner", "Philip K. Dick", "Hachette", 1968, 600);
			Book livre3 = new Book("Le Seigneur des Anneaux", "JRR Tolkien", "Hachette", 1954, 10000);
			bookRepository.save(livre1);
			bookRepository.save(livre2);
			bookRepository.save(livre3);
			Author author1 = new Author("Isaac Asimov", "Créateur des 3 lois de la robotique");
			Author author2 = new Author("Philip K Dick", "Pilier de la SF, grand inspirateur du cinéma");
			Author author3 = new Author("JRR Tolkien", "Père de la fantasy moderne");
			authorRepository.save(author1);
			authorRepository.save(author2);
			authorRepository.save(author3);
			Borrowing borrowing1 = new Borrowing(1, LocalDate.parse("2024-03-01"), LocalDate.parse("2024-03-30"));
			Borrowing borrowing2 = new Borrowing(1, LocalDate.parse("2024-03-01"), LocalDate.parse("2024-03-15"));
			borrowingRepository.save(borrowing1);
			borrowingRepository.save(borrowing2);
		};
	}

}
