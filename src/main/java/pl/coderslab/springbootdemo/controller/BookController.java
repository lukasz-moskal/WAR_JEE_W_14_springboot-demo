package pl.coderslab.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.springbootdemo.entity.Book;
import pl.coderslab.springbootdemo.service.BookService;

import javax.validation.Valid;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "/book/add", produces = "text/html;charset=UTF-8")
    String showAddForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "book/add";
    }

    @PostMapping(path = "/book/add", produces = "text/html;charset=UTF-8")
    String processAddForm(@Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "book/add";
        } else {
            bookService.addBook(book);
        }

        return "redirect:/book/list";
    }

    @GetMapping(path = "/book/list", produces = "text/html;charset=UTF-8")
    String showAllBooks(Model model) {

        model.addAttribute("books", bookService.findAllBooks());

        return "book/list";
    }
}
