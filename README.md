# BookApp
A simple Spring Boot book application, which saves randomly generated book data to an In-Memory H2 Database at start up.  
This is handled by a Book Repository Interface of JpaRepository covering the Book Entity.  

Accessible Endpoints at http://localhost:8080:

| HTTP Methode | URL | Decription |
| --- | --- | --- |
| GET: | "/api/listBooks" | List all Books
| GET: | "/api/book/{id}" | List one Book
| DELETE: | "/book/{id}" | Delete one Book
| PUT: | "/book" | Put one Book
| PATCH: | "/book" | Patch one Book


Tests are in the Test Folder:

| File name |
| --- |
| BookControllerTests |
| BookServiceTests |
| BookRepositoryTests |


CI/CD:
Java with Maven  
Build & Push Docker image
