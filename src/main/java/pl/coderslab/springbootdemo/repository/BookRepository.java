package pl.coderslab.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.springbootdemo.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> { }
