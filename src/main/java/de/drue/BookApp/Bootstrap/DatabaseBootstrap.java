package de.drue.BookApp.Bootstrap;


import com.github.javafaker.Faker;
import de.drue.BookApp.Entity.Book;
import de.drue.BookApp.Repository.BookRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Locale;

@Slf4j
@NoArgsConstructor
public class DatabaseBootstrap implements InitializingBean {

    @Autowired
    private BookRepository bookRepository;

    private Book tmpBook;
    @Override
    public void afterPropertiesSet() throws Exception {
        Faker faker=new Faker(new Locale("de"));

        for (int i = 0; i < 10; i++){
            tmpBook = Book.builder()
                    .title(faker.book().title())
                    .author(faker.book().author())
                    .genre(faker.book().genre())
                    .publisher(faker.book().publisher())
                    .build();

            bookRepository.save(tmpBook);
            log.info(String.format("Bootstrap: Book added: %s", tmpBook.toString()));
        }
        log.info("Bootstrap finished");
    }
}
