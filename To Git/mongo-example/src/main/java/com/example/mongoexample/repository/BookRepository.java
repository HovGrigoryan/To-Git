package com.example.mongoexample.repository;

import com.example.mongoexample.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book,String> {


}
