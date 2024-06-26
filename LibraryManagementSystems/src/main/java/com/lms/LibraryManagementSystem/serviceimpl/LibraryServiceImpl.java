package com.lms.LibraryManagementSystem.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lms.LibraryManagementSystem.dao.LibraryRepository;
import com.lms.LibraryManagementSystem.entity.Book;
import com.lms.LibraryManagementSystem.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryRepository libraryRepository;

	@Override
	public void addBook(Book book) {
		List<Book> existingBooks = libraryRepository.findByIsbnAndDepartmentNot(book.getIsbn(), book.getDepartment());
		if (!existingBooks.isEmpty()) {
			throw new IllegalArgumentException("A book with the same ISBN already exists in another department.");
		}
		libraryRepository.save(book);

	}

	@Override
	public void removeBook(String isbn) {
		libraryRepository.deleteById(isbn);

	}

	@Override
	public List<Book> findBookByTitle(String title) {
		return libraryRepository.findByTitleIgnoreCase(title);
	}

	@Override
	public List<Book> findBookByAuthor(String author) {
		return libraryRepository.findByAuthorIgnoreCase(author);
	}

	@Override
	public List<Book> listAllBooks() {
		return libraryRepository.findAll();
	}

	@Override
	public List<Book> listAvailableBooks() {
		return libraryRepository.findByAvailable(true);
	}
}
