package com.doranco.examspring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ExamSpringApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ExamSpringApplication.class, args);

       
        RestTemplate restTemplate = new RestTemplate();

        addBook(restTemplate);
        updateBook(restTemplate);
        deleteBook(restTemplate);
        searchBooks(restTemplate);
        addAuthor(restTemplate);
        updateAuthor(restTemplate);
        deleteAuthor(restTemplate);
        searchAuthors(restTemplate);
        addBorrowing(restTemplate);
        updateBorrowing(restTemplate, 0);
        deleteBorrowing(restTemplate, 0);
        searchBorrowings(restTemplate, null);
    }

    private static void addBook(RestTemplate restTemplate) {
        String url = "http://localhost:8080/books";
        String requestBody = "{\"title\":\"Le Petit Prince\",\"author\":\"Antoine de Saint-Exupéry\",\"publisher\":\"Gallimard\",\"publicationYear\":1943,\"pageCount\":128}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        System.out.println("Code de statut (Ajout de livre) : " + statusCode);
    }

    private static void updateBook(RestTemplate restTemplate) {
        long bookId = 1;
        String url = "http://localhost:8080/books/" + bookId;
        String requestBody = "{\"title\":\"Le Petit Prince\",\"author\":\"Antoine de Saint-Exupéry\",\"publisher\":\"Gallimard\",\"publicationYear\":1943,\"pageCount\":128}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        System.out.println("Code de statut (Mise à jour de livre) : " + statusCode);
    }

    private static void deleteBook(RestTemplate restTemplate) {
        long bookId = 1;
        String url = "http://localhost:8080/books/" + bookId;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        System.out.println("Code de statut (Suppression de livre) : " + statusCode);
    }

    private static void searchBooks(RestTemplate restTemplate) {
        String title = "Le Petit Prince";
        String author = "Antoine de Saint-Exupéry";
        String publisher = "Gallimard";
        String url = "http://localhost:8080/books?title=" + title + "&author=" + author + "&publisher=" + publisher;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        System.out.println("Code de statut (Recherche de livre) : " + statusCode);
        String responseBody = responseEntity.getBody();
        System.out.println("Réponse du serveur (Recherche de livre) : " + responseBody);
    }

    private static void addAuthor(RestTemplate restTemplate) {
        String url = "http://localhost:8080/authors";
        String requestBody = "{\"name\":\"Antoine de Saint-Exupéry\",\"biography\":\"Antoine de Saint-Exupéry was a French writer, poet, aristocrat, journalist, and pioneering aviator.\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        System.out.println("Code de statut (Ajout d'auteur) : " + statusCode);
    }

    private static void updateAuthor(RestTemplate restTemplate) {
        long authorId = 1;
        String url = "http://localhost:8080/authors/" + authorId;
        String requestBody = "{\"name\":\"Antoine de Saint-Exupéry\",\"biography\":\"Antoine de Saint-Exupéry was a French writer, poet, aristocrat, journalist, and pioneering aviator.\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        System.out.println("Code de statut (Mise à jour d'auteur) : " + statusCode);
    }

    private static void deleteAuthor(RestTemplate restTemplate) {
        long authorId = 1;
        String url = "http://localhost:8080/authors/" + authorId;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        System.out.println("Code de statut (Suppression d'auteur) : " + statusCode);
    }

    private static void searchAuthors(RestTemplate restTemplate) {
        String authorName = "Antoine de Saint-Exupéry";
        String url = "http://localhost:8080/authors?name=" + authorName;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        System.out.println("Code de statut (Recherche d'auteur) : " + statusCode);
        String responseBody = responseEntity.getBody();
        System.out.println("Réponse du serveur (Recherche d'auteur) : " + responseBody);
    
    
    
    
    }
    
    private static void addBorrowing(RestTemplate restTemplate) {
    	  String url = "http://localhost:8080/borrowings";
    	    String requestBody = "{\"bookId\":1,\"borrowDate\":\"2023-03-10\",\"dueDate\":\"2023-03-31\"}";
    	    HttpHeaders headers = new HttpHeaders();
    	    headers.setContentType(MediaType.APPLICATION_JSON);
    	    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
    	    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    	    HttpStatusCode statusCode = responseEntity.getStatusCode();
    	    System.out.println("Code de statut (Ajout d'emprunt) : " + statusCode);
    }

    private static void updateBorrowing(RestTemplate restTemplate, long borrowingId) {
    	 String url = "http://localhost:8080/borrowings/" + borrowingId;
    	    String requestBody = "{\"bookId\":1,\"borrowDate\":\"2023-03-10\",\"dueDate\":\"2023-03-31\"}";
    	    HttpHeaders headers = new HttpHeaders();
    	    headers.setContentType(MediaType.APPLICATION_JSON);
    	    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
    	    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
    	    HttpStatusCode statusCode = responseEntity.getStatusCode();
    	    System.out.println("Code de statut (Mise à jour d'emprunt) : " + statusCode);
    }

    private static void deleteBorrowing(RestTemplate restTemplate, long borrowingId) {
    	 String url = "http://localhost:8080/borrowings/" + borrowingId;
    	    HttpHeaders headers = new HttpHeaders();
    	    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    	    ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class);
    	    HttpStatusCode statusCode = responseEntity.getStatusCode();
    	    System.out.println("Code de statut (Suppression d'emprunt) : " + statusCode);
    }

    private static void searchBorrowings(RestTemplate restTemplate, String status) {
    	String url = "http://localhost:8080/borrowings?status=" + status;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        System.out.println("Code de statut (Recherche d'emprunts) : " + statusCode);

        String responseBody = responseEntity.getBody();
        System.out.println("Réponse du serveur (Recherche d'emprunts) : " + responseBody);
    }
    
    
    
    
    }

  
    
    


    

