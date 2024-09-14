package hu.springbootrestfuljpa.books.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import hu.springbootrestfuljpa.books.model.Book;
import hu.springbootrestfuljpa.books.model.Review;
import hu.springbootrestfuljpa.books.repository.BookRepository;
import hu.springbootrestfuljpa.books.repository.ReviewRepository;

class ReviewControllerTest {

	private static final int ID_REVIEW_1 = 5;
	private static final int ID_REVIEW_2 = 7;
	private static final String DESCRIPTION_1 = "El mundo es ancho y ajeno";
	private static final String DESCRIPTION_2 = "Don Quijote de la Mancha";
	private static final int ID = 3;
	private static final int RELEASE = 15;
	private static final String AUTHOR = "Homero";
	private static final String TITLE = "Odisea";
	
	private static final Review REVIEW_1 = new Review();
	private static final Review REVIEW_2 = new Review();
	private static final Book BOOK = new Book();
	private static final Optional<Book> OPTIONAL_BOOK = Optional.of(BOOK);
	private static final Optional<Book> OPTIONAL_BOOK_EMPTY = Optional.empty();
	private static final List<Review> REVIEW_LIST = new ArrayList<>(); 
	
	@Mock
	private BookRepository bookRepository;
	
	
	@Mock
	private ReviewRepository reviewRepository;
	
	@InjectMocks
	ReviewController controller;	
	
	@BeforeEach
	public void init() {
		
		MockitoAnnotations.initMocks(this);
	
		REVIEW_1.setId(ID_REVIEW_1);
		REVIEW_1.setDescription(DESCRIPTION_1);
		REVIEW_2.setId(ID_REVIEW_2);
		REVIEW_2.setDescription(DESCRIPTION_2);
		REVIEW_LIST.add(REVIEW_2);
		
		BOOK.setId(ID);
		BOOK.setAuthor(AUTHOR);	
		BOOK.setRelease(RELEASE);
		BOOK.setReviews(REVIEW_LIST);
		BOOK.setTitle(TITLE);
	}
	
	@Test
	void testNotFoundRetrieveAllReviews() {
		
		Mockito.when(bookRepository.findById(ID)).thenReturn(OPTIONAL_BOOK_EMPTY);
		
		ResponseEntity<List<Review>> response = controller.retrieveAllReviews(ID);
		
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}
	
	
	@Test
	void testIsPresentRetrieveAllReviews() {
		
		Mockito.when(bookRepository.findById(ID)).thenReturn(OPTIONAL_BOOK);
		
		ResponseEntity<List<Review>> response = controller.retrieveAllReviews(ID);
					
		assertEquals(response.getBody(), REVIEW_LIST);
	}

	@Test
	void testNotIsPresentCreateReview() {
		
		Mockito.when(bookRepository.findById(ID)).thenReturn(OPTIONAL_BOOK_EMPTY);
		
		ResponseEntity<Object> response = controller.createReview(ID, REVIEW_1);
		
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	@Test
	void testIsPresentCreateReview() {
		
		Mockito.when(bookRepository.findById(BOOK.getId())).thenReturn(OPTIONAL_BOOK);
		
		ResponseEntity<Object> response = controller.createReview(BOOK.getId(), REVIEW_2);			
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
}
