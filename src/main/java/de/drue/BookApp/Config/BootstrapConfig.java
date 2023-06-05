package de.drue.BookApp.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import de.drue.BookApp.Bootstrap.DatabaseBootstrap;

@Configuration
public class BootstrapConfig {
    @Bean
    public DatabaseBootstrap databaseBootstrap() {
        return new DatabaseBootstrap();
    }
}
