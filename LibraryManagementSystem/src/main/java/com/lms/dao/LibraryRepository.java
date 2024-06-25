package com.lms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.lms.entity.Book;

@Repository
@EnableJpaRepositories
public interface LibraryRepository extends JpaRepository<Book, String> {

	List<Book> findByTitleIgnoreCase(String title);

	List<Book> findByAuthorIgnoreCase(String author);

	List<Book> findByAvailable(boolean available);

}
