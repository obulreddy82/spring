package com.rajtechnologies.model;

public class Book {

    private String title;
    private String author;
    private String isbn;
    private String id;

    public Book() {
        super();
    }

    public Book(String id, String title, String author, String isbn) {
        super();
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
