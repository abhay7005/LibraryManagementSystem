package com.lms.LibraryManagementSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.LibraryManagementSystem.controller.LibraryController;
import com.lms.LibraryManagementSystem.entity.Book;
import com.lms.LibraryManagementSystem.service.LibraryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LibraryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LibraryService libraryService;

    @InjectMocks
    private LibraryController libraryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(libraryController).build();
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book("1101", "Chetan Bhagat", "ISBN1234567890", "Novel", 2008, "Romance", true);

        mockMvc.perform(post("/library/addBook")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk());

        verify(libraryService, times(1)).addBook(book);
    }

    @Test
    public void testRemoveBook() throws Exception {
        String isbn = "12345";

        mockMvc.perform(delete("/library/removeBook/{isbn}", isbn))
                .andExpect(status().isOk());

        verify(libraryService, times(1)).removeBook(isbn);
    }

    @Test
    public void testFindBookByTitle() throws Exception {
        String title = "Test Title";
        List<Book> books = Arrays.asList(new Book("1101", "Chetan Bhagat", "ISBN1234567890", "Novel", 2008, "Romance", true));

        when(libraryService.findBookByTitle(anyString())).thenReturn(books);

        mockMvc.perform(get("/library/findBookByTitle/{title}", title))
                .andExpect(status().isOk());
        verify(libraryService, times(1)).findBookByTitle(title);
    }

    @Test
    public void testFindBookByAuthor() throws Exception {
        String author = "Test Author";
        List<Book> books = Arrays.asList(new Book("1101", "Chetan Bhagat", "ISBN1234567890", "Novel", 2008, "Romance", true));

        when(libraryService.findBookByAuthor(anyString())).thenReturn(books);

        mockMvc.perform(get("/library/findBookByAuthor/{author}", author))
                .andExpect(status().isOk());
        verify(libraryService, times(1)).findBookByAuthor(author);
    }

    @Test
    public void testListAllBooks() throws Exception {
        Book book = new Book("1101", "Chetan Bhagat", "ISBN1234567890", "Novel", 2008, "Romance", true);
        when(libraryService.listAllBooks()).thenReturn(List.of(book));

        mockMvc.perform(get("/library/listAllBooks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn").value(book.getIsbn()));

        verify(libraryService, times(1)).listAllBooks();
    }

    @Test
    public void testListAvailableBooks() throws Exception {
        Book book = new Book("1101", "Chetan Bhagat", "ISBN1234567890", "Novel", 2008, "Romance", true);
        when(libraryService.listAvailableBooks()).thenReturn(List.of(book));

        mockMvc.perform(get("/library/listAvailableBooks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].available").value(true));

        verify(libraryService, times(1)).listAvailableBooks();
    }
}
