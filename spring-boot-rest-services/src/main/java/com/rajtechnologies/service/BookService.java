package com.rajtechnologies.service;

import com.rajtechnologies.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();

    public BookService() {
        books.add(new Book("1", "Dream Light", "Abdul Kalam", "ISBN-001"));
        books.add(new Book("2", "Core Java", "Boby", "ISBN-001"));
        books.add(new Book("3", "Advanced Java", "Rekha", "ISBN-001"));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(String id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst().orElse(null);
    }

    public void deleteAll() {
        books.clear();
    }
}
