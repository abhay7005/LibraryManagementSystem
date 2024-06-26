package com.lms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Data
@Getter
@Setter
public class Book {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private String id;
	@Column(name = "Title", unique = true, nullable = false)
	@Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$", message = "Invalid title")
	private String title;
	@NotBlank(message = "Author name is manadtory")
	@Column(name = "AuthorName", nullable = false)
	@Pattern(regexp = "^[a-zA-Z]+(\\s[a-zA-Z]+)*$", message = "Invalid Author name")
	private String author;
	@Id
	@Column(name = "ISBN", unique = true, nullable = false)
	@Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$", message = "Invalid International Standard Book Number format")
	private String isbn;
	@NotBlank(message = "Genres name is manadtory")
	@Column(name = "Genres", nullable = false)
	@Pattern(regexp = "^[a-zA-Z]+(\\s[a-zA-Z]+)*$", message = "Invalid Categories")
	private String genre;
	@Column(name = "PublicationYear")
	@Min(value = 1000, message = "Publication Year should not be less than 1000")
	@Max(value = 9999, message = "Publication Year should not be more than 9999")
	private int publicationYear;
	@Column(name = "Department", nullable = false)
	private String department;
	@Column(name = "Available", nullable = false)
	private boolean available;

	public Book() {
	}

	public Book(String title, String author, String isbn, String genre, int publicationYear, String department,
			boolean available) {
		super();
//		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.genre = genre;
		this.publicationYear = publicationYear;
		this.department = department;
		this.available = available;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Book book = (Book) obj;

		return isbn != null ? isbn.equals(book.isbn) : book.isbn == null;
	}

	@Override
	public int hashCode() {
		return isbn != null ? isbn.hashCode() : 0;
	}
}
