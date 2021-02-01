package tech.talci.talcistorespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TalcistoreSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalcistoreSpringApplication.class, args);
    }

}
