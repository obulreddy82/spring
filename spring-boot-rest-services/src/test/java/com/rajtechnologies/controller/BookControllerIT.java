package com.rajtechnologies.controller;

import com.rajtechnologies.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIT {

    @Autowired
    private BookService bookService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllBooks() throws Exception {
        mockMvc.perform(get("/api/books/findAll"))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$[0].title")
                                .value("Dream Light")
                );
    }
}
