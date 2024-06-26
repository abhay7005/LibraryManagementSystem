package com.lms.serviceimpl;

import com.lms.dao.LibraryRepository;
import com.lms.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {LibraryServiceImplTest.class})
@ExtendWith(MockitoExtension.class)
public class LibraryServiceImplTest {

    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private LibraryServiceImpl libraryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBook() {
        Book book = new Book("1101",
                "Chetan Bhagat", "ISBN1234567890", "Novel",
                2008, "Romance", true);
        libraryService.addBook(book);
        verify(libraryRepository, times(1)).save(book);
    }

    @Test
    public void testRemoveBook() {
        String isbn = "12345";
        libraryService.removeBook(isbn);
        verify(libraryRepository, times(1)).deleteById(isbn);
    }

    @Test
    public void testFindBookByTitle() {
        String title = "Test Title";
        List<Book> books = List.of(new Book("1101",
                "Chetan Bhagat", "ISBN1234567890", "Novel",
                2008, "Romance", true));
        when(libraryRepository.findByTitleIgnoreCase(title)).thenReturn(books);

        List<Book> foundBooks = libraryService.findBookByTitle(title);
        assertEquals(books, foundBooks);
        verify(libraryRepository, times(1)).findByTitleIgnoreCase(title);
    }

    @Test
    public void testFindBookByAuthor() {
        String author = "Test Author";
        List<Book> books = List.of(new Book("1101",
                "Chetan Bhagat", "ISBN1234567890", "Novel",
                2008, "Romance", true));
        when(libraryRepository.findByAuthorIgnoreCase(author)).thenReturn(books);

        List<Book> foundBooks = libraryService.findBookByAuthor(author);
        assertEquals(books, foundBooks);
        verify(libraryRepository, times(1)).findByAuthorIgnoreCase(author);
    }

    @Test
    public void testListAllBooks() {
        List<Book> books = List.of(new Book("1101",
                "Chetan Bhagat", "ISBN1234567890", "Novel",
                2008, "Romance", true));
        when(libraryRepository.findAll()).thenReturn(books);

        List<Book> foundBooks = libraryService.listAllBooks();
        assertEquals(books, foundBooks);
        verify(libraryRepository, times(1)).findAll();
    }

    @Test
    public void testListAvailableBooks() {
        List<Book> books = List.of(new Book("1101",
                "Chetan Bhagat", "ISBN1234567890", "Novel",
                2008, "Romance", true));
        when(libraryRepository.findByAvailable(true)).thenReturn(books);

        List<Book> availableBooks = libraryService.listAvailableBooks();
        assertEquals(books, availableBooks);
        verify(libraryRepository, times(1)).findByAvailable(true);
    }
}
