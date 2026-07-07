package com.rajtechnologies.controller;

import com.rajtechnologies.model.Book;
import com.rajtechnologies.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/findAll")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }


    @GetMapping("/findById/{id}")
    public Book getAllBooks(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAllBooks() {
        bookService.deleteAll();
        return "All books have been deleted.";
    }
}
