package de.drue.BookApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class BookApp {

	public static void main(final String[] args) {
		SpringApplication.run(BookApp.class, args);
	}

}
