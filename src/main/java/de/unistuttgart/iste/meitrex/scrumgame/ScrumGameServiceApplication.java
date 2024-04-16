package de.unistuttgart.iste.meitrex.scrumgame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the entry point of the application.
 */
@SpringBootApplication
@Slf4j
public class ScrumGameServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrumGameServiceApplication.class, args);
    }

}
