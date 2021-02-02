package tech.talci.talcistorespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import tech.talci.talcistorespring.config.SwaggerConfiguration;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class TalcistoreSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalcistoreSpringApplication.class, args);
    }

}
