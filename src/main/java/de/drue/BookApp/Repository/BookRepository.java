package de.drue.BookApp.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import de.drue.BookApp.Entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
