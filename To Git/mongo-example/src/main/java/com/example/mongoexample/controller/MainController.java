package com.example.mongoexample.controller;


import com.example.mongoexample.model.Book;
import com.example.mongoexample.model.User;
import com.example.mongoexample.repository.BookRepository;
import com.example.mongoexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
  public String main(){
        return "index";
    }

    @PostMapping("/user/add")
    public String addUser (@ModelAttribute User user){
        Book book = user.getBook();
        bookRepository.save(book);
        userRepository.save(user);
        return "redirect:/";
    }

}
