package hu.springbootrestfuljpa.books.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import hu.springbootrestfuljpa.books.repository.BookRepository;
import hu.springbootrestfuljpa.books.repository.ReviewRepository;

class ReviewControllerTest {

	@Mock
	private BookRepository bookRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@InjectMocks
	ReviewController controller;	
	
	@BeforeEach
	public void init() {
		
		MockitoAnnotations.initMocks(this);
	/*
		BOOK.setId(ID);
		BOOK.setAuthor(AUTHOR);	
		BOOK.setRelease(RELEASE);
		BOOK.setReviews(REVIEW_LIST);
		BOOK.setTitle(TITLE);*/
	}
	
	@Test
	void testRetrieveAllReviews() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateReview() {
		fail("Not yet implemented");
	}

}
