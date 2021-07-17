package am.itspace.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@SpringBootApplication
@ComponentScan({"am.itspace.common.*", "am.itspace.rest.*"})
@EnableJpaRepositories(basePackages = {"am.itspace.common.*", "am.itspace.rest.*"})
@EntityScan("am.itspace.common.*")
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

}
