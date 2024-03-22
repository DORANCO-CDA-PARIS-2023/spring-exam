# Conception et développement d'une API REST pour une application de gestion de bibliothèque


## Objectifs

L'objectif de cet exercice est de concevoir et de développer une API REST pour une application de gestion de bibliothèque. L'API doit permettre de gérer les livres, les auteurs et les emprunts.

Les fonctionnalités attendues sont les suivantes :

- [x] Mettre à jour les informations d'un livre existant.
- [x] Mettre à jour les informations d'un livre existant.
- [x] Supprimer un livre de la bibliothèque.
- [x] Rechercher des livres par titre, auteur ou éditeur.
- [ ] Ajouter un nouvel auteur avec son nom et sa biographie.
- [ ] Mettre à jour les informations d'un auteur existant.
- [ ] Supprimer un auteur de la bibliothèque.
- [ ] Rechercher des auteurs par nom.
- [ ] Ajouter un nouvel emprunt avec le livre emprunté, la date d'emprunt et la date de retour prévue.
- [ ] Mettre à jour les informations d'un emprunt existant.
- [ ] Supprimer un emprunt de la bibliothèque.
- [ ] Rechercher des emprunts en cours ou passés.

## Documentation 
Voici une liste de routes et de descriptions pour l'API REST de gestion de bibliothèque décrite précédemment :

1. Ajouter un nouveau livre 
    ```
    Path : /books 
    Méthode : POST
    Payload :
    
    {
    "title": "Le Petit Prince",
    "author": "Antoine de Saint-Exupéry",
    "publisher": "Gallimard",
    "publicationYear": 1943,
    "pageCount": 128
    }
    Status : 201 Created
    ```

2. Mettre à jour les informations d'un livre existant
    ```
    Path : /books/{bookId}
    Méthode : PUT
    Payload :
    
    {
    "title": "Le Petit Prince",
    "author": "Antoine de Saint-Exupéry",
    "publisher": "Gallimard",
    "publicationYear": 1943,
    "pageCount": 128
    }
    Status : 200 OK
    ```

3. Supprimer un livre de la bibliothèque
    ```
    Path : /books/{bookId}
    Méthode : DELETE
    Status : 204 No Content
      ```
   
4. Rechercher des livres par titre, auteur ou éditeur
    ```
    Path : /books?title={title}&author={author}&publisher={publisher}
    Méthode : GET
    Payload : aucun
    Status : 200 OK
    ```
 

5. Ajouter un nouvel auteur
    ```
    Path : /authors
    Méthode : POST
    Payload :
    
    {
    "name": "Antoine de Saint-Exupéry",
    "biography": "Antoine de Saint-Exupéry was a French writer, poet, aristocrat, journalist, and pioneering aviator."
    }
    Status : 201 Created
    ```

6. Mettre à jour les informations d'un auteur existant
    ```
    Path : /authors/{authorId}
    Méthode : PUT
    Payload :
    
    {
    "name": "Antoine de Saint-Exupéry",
    "biography": "Antoine de Saint-Exupéry was a French writer, poet, aristocrat, journalist, and pioneering aviator."
    }
    Status : 200 OK
    ```

7. Supprimer un auteur de la bibliothèque
    ```
    Path : /authors/{authorId}
    Méthode : DELETE
    Status : 204 No Content
    ```
   
8. Rechercher des auteurs par nom
    ``` 
    Path : /authors?name={name}
   Méthode : GET
   Payload : aucun
   Status : 200 OK
    ```

9. Ajouter un nouvel emprunt
    ```
    Path : /borrowings
    Méthode : POST
    Payload :
    
    {
    "bookId": 1,
    "borrowDate": "2023-03-10",
    "dueDate": "2023-03-31"
    }
    Status : 201 Created
    ```
    
10. Mettre à jour les informations d'un emprunt existant
    ```
    Path : /borrowings/{borrowingId}
    Méthode : PUT
    Payload :
    
    {
    "bookId": 1,
    "borrowDate": "2023-03-10",
    "dueDate": "2023-03-31"
    }
    Status : 200 OK
    ```

11. Supprimer un emprunt de la bibliothèque
    ```
    Path : /borrowings/{borrowingId}
    Méthode : DELETE
    Status : 204 No Content
    ```

12. Rechercher des emprunts en cours ou passés
    ```
    Path : /borrowings?status={status}
    Méthode : GET
    Payload : aucun
    Status : 200 OK
    ```