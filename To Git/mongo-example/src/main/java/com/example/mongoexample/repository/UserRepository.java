package com.example.mongoexample.repository;

import com.example.mongoexample.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {


}
