package com.lms.service;

import java.util.List;

import com.lms.entity.Book;

import jakarta.transaction.Transactional;
@Transactional
public interface LibraryService {

	void addBook(Book book);

	void removeBook(String isbn);

	List<Book> findBookByTitle(String title);

	List<Book> findBookByAuthor(String author);

	List<Book> listAllBooks();

	List<Book> listAvailableBooks();

}
