package am.itspace.experience;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ExperienceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExperienceApplication.class, args);
    }

}
