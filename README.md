# BookApp
A Spring Boot Application, which saves randomly generated book data to an In-Memory H2 Database at start up.  
  
  
Accessible Endpoints at localhost:8080  
  
| HTTP Methode | URL | Decription |
| --- | --- | --- |
| GET: | "/api/listBooks" | List all Books
| GET: | "/api/book/{id}" | List one Book
| DELETE: | "/book/{id}" | Delete one Book
| PUT: | "/book" | Put one Book
| PATCH: | "/book" | Patch one Book
  
  
Tests are in the Test Folder:  
  
| Filename |
| --- |
| BookControllerTests |
| BookServiceTests |
| BookRepositoryTests |
  
  
CI/CD:  
Java with Maven  
Build & Push Docker image  
  
Super Linter