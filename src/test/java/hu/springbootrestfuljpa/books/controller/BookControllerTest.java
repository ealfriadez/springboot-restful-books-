package hu.springbootrestfuljpa.books.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import hu.springbootrestfuljpa.books.model.Book;
import hu.springbootrestfuljpa.books.model.Review;
import hu.springbootrestfuljpa.books.repository.BookRepository;
import hu.springbootrestfuljpa.books.util.Utilidades;

public class BookControllerTest {
	
	private static final int ID = 3;
	private static final int RELEASE = 15;
	private static final String AUTHOR = "Homero";
	private static final String TITLE = "Odisea";
	
	private static final Book BOOK = new Book();
	private static final Optional<Book> OPTIONAL_BOOK = Optional.of(BOOK);
	private static final Optional<Book> OPTIONAL_BOOK_EMPTY = Optional.empty();
	private static final List<Review> REVIEW_LIST = new ArrayList<>(); 
	
	@Mock
	private BookRepository bookRepository;
	
	@InjectMocks
	BookController controller;	
	
	@BeforeEach
	public void init() {
		
		MockitoAnnotations.initMocks(this);
	
		BOOK.setId(ID);
		BOOK.setAuthor(AUTHOR);	
		BOOK.setRelease(RELEASE);
		BOOK.setReviews(REVIEW_LIST);
		BOOK.setTitle(TITLE);
	}	
	
	@Test
	void retrieveAllBooksTest() {		
		
		final Book book = new Book();
		Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(book));
		
		final List<Book> response = controller.retrieveAllBooks();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 1);
	}
	
	@Test
	void retrieveBookTest() {		
		
		Mockito.when(bookRepository.findById(ID)).thenReturn(OPTIONAL_BOOK);
	
		ResponseEntity<Book> response = controller.retrieveBook(ID);
			
		assertEquals(response.getBody().getAuthor(), AUTHOR);
		assertEquals(response.getBody().getTitle(), TITLE);
	}
	
	@Test
	void retrieveBookNotFoundTest() {		
		
		Mockito.when(bookRepository.findById(ID)).thenReturn(OPTIONAL_BOOK_EMPTY);
	
		ResponseEntity<Book> response = controller.retrieveBook(ID);
			
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	@Test
	void createBookTest() {		
		
		Mockito.when(bookRepository.existsById(BOOK.getId())).thenReturn(false);
	
		ResponseEntity<Object> response = controller.createBook(BOOK);
			
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
		
	@Test
	void createBookExistsByIdTest() {		
		
		Mockito.when(bookRepository.existsById(BOOK.getId())).thenReturn(true);
	
		ResponseEntity<Object> response = controller.createBook(BOOK);
			
		assertEquals(response.getStatusCode(), HttpStatus.CONFLICT);
	}
	
	
	@Test
	void deleteBookNotFoundTest() {		
		
		Mockito.when(bookRepository.findById(ID)).thenReturn(OPTIONAL_BOOK_EMPTY);
	
		ResponseEntity<Object> response = controller.deleteBook(ID);
			
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}
	
	
	@Test
	void deleteBookFoundTest() {		
		
		Mockito.when(bookRepository.findById(ID)).thenReturn(OPTIONAL_BOOK);
		
		controller.deleteBook(ID);
	}
}

