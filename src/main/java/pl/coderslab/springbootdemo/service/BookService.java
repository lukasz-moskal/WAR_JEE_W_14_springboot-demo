package pl.coderslab.springbootdemo.service;

import org.springframework.stereotype.Service;
import pl.coderslab.springbootdemo.entity.Book;
import pl.coderslab.springbootdemo.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
}
