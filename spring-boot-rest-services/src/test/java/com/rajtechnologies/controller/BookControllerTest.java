package com.rajtechnologies.controller;

import com.rajtechnologies.model.Book;
import com.rajtechnologies.service.BookService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {
    @InjectMocks
    BookController bookController;

    @Mock
    BookService bookService;

    @BeforeEach
    void setUp() {
        System.out.println("Before each");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all");
    }


    @Test
    void getAllBooks() {
        //given
        List<Book> books = new ArrayList<>();
        Book book = new Book("1", "Dream Light", "Abdul Kalam", "ISBN-001");
        books.add(book);
        when(bookService.getAllBooks()).thenReturn(books);
        //when
        List<Book> result = bookController.getAllBooks();
        //then
        assertEquals(1, result.size());
        assertEquals("Dream Light", result.get(0).getTitle());
    }

    @Test
    void testGetAllBooks() {
    }

    @Test
    void deleteAllBooks() {
    }

    @Test
    void givenCalculator_whenAdd_thenSum() {
        int result = bookController.calculator(1, 2);
        assertEquals(3, result);
    }

    @ParameterizedTest
    @CsvSource({"1, 2, 3", "2, 3, 5"})
    void multipleTestScenariosForCalculator_csvSource(int a, int b, int c) {
        int result = bookController.calculator(a, b);
        assertEquals(c, result);
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    void multipleTestScenariosForCalculator_methodSource(int a, int b, int c) {
        int result = bookController.calculator(a, b);
        assertEquals(c, result);
    }

    static Stream<Arguments> provideNumbers() {
        return Stream.of(Arguments.of(1, 2, 3), Arguments.of(2, 3, 5));
    }
}
