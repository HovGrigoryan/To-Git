package am.itspace.web.controller;

import am.itspace.common.model.Book;
import am.itspace.common.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/books")
    public String books(ModelMap modelMap) {
        List<Book> books = bookRepository.findAll();
        modelMap.addAttribute("books",books);
        return "books";


    }
}
