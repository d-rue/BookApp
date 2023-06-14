# BookApp
A Spring Boot Application, which saves randomly generated book data to an In-Memory H2 Database at start up.  


Accessible Endpoints  
dev-Environment: localhost:8080  
prod-Environment: localhost:80

| HTTP Methode | URL | Decription      |
|--------------| --- |-----------------|
| GET:         | "/api/listBooks" | List all Books  |
| GET:         | "/api/book/{id}" | List one Book   |
| DELETE:      | "/api/book/{id}" | Delete one Book |
| PUT:         | "/api/book" | Put one Book    |
| PATCH:       | "/api/book" | Patch one Book  |
| POST:        | "/api/book" | Create one Book |

OpenAPI/Swagger in dev-Environment at localhost:8080/swagger-ui.html


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