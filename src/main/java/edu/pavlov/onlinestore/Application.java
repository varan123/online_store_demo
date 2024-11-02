package edu.pavlov.onlinestore;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The Application class is the entry point of the Spring Boot application.
 * The @SpringBootApplication annotation is used to mark the class as a
 * Spring Boot application.
 * The @ComponentScan annotation is used to specify the base packages to scan
 * for annotated components.
 */
@SpringBootApplication
@ComponentScan
public class Application {
    /**
     * The main() method is the entry point of the Spring Boot application.
     * The SpringApplication.run() method is used to run the Spring Boot
     * application.
     * @param args The command line arguments.
     */
    public static void main(final String[] args) {
        new Application().run(args);
    }

    private void run(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
