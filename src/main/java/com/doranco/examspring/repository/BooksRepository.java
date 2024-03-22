package com.doranco.examspring.repository;

import com.doranco.examspring.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {

    public List<Books> findBooksByTitle(String titre);
    public List<Books> findBooksByAuteur(String auteur);
    public List<Books> findBooksByEditeur(String editeur);

    @Query("SELECT b FROM Books c WHERE b.titre LIKE %:titre%")
    public List<Books> searchByTitle(@Param("titre") String titre);

    @Query("SELECT b FROM Books c WHERE b.auteur LIKE %:auteur%")
    public List<Books> searchBooksByAuthor(@Param("auteur") String auteur);

    @Query("SELECT b FROM Books c WHERE b.editeur LIKE %:editeur%")
    public List<Books> searchBooksByPublisher(@Param("editeur") String editeur);
}
