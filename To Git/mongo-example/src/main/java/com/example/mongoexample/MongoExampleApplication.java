package com.example.mongoexample;

import com.example.mongoexample.model.User;
import com.example.mongoexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoExampleApplication.class, args);
    }


    }

