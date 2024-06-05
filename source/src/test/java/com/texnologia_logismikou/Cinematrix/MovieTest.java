package com.texnologia_logismikou.Cinematrix;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MovieTest {

	//Test method for Movie.setReleaseDate()
	//The number that will be set needs to be checked. It can't be negative or too big.
	@Test
	void setReleaseDateDateNegativeNumber() {
		
		Movie tempMovie = new Movie();
		int releaseDate = -10;
		tempMovie.setReleaseDate(releaseDate);
		
		assertTrue(tempMovie.getReleaseDate() > -1, "The release date is a negative number!");
	}
	
	//Same test as above but with a very big number.
	@Test
	void setReleaseDateBigNumber() {
		
		Movie tempMovie = new Movie();
		int releaseDate = 40;
		tempMovie.setReleaseDate(releaseDate);
		
		assertTrue(tempMovie.getReleaseDate() < 32, "The release date is bigger than it can be!");
	}
	
	//Same test as above but with a number between 0 and 31.
	@Test
	void setReleaseDateExpectedNumber() {
		
		Movie tempMovie = new Movie();
		int releaseDate = 23;
		tempMovie.setReleaseDate(releaseDate);
		
		assertTrue(tempMovie.getReleaseDate() > -1 && tempMovie.getReleaseDate() < 32, "The number isn't between 1, 31");
	}
	
	//Test method for Movie.setExpireDate()
	@Test
	void setExpireDateSmallerThanRelease() {
		
		Movie tempMovie = new Movie();
		int expireDate = 15;
		int releaseDate = 20;
		tempMovie.setExpireDate(expireDate);
		tempMovie.setReleaseDate(releaseDate);
		
		assertEquals(expireDate, 0);
	}
	
	//How can the code pass the Unit tests?
	//For the Unit tests to pass, the methods used must produce the expected result. 
	//setReleaseDate(): The expected behavior should be to set the release date as specified by the argument 
	//if the number is between 1-31, and set the release date as 0 in any other case as an error value.
	//The same applies to Movie.duration getter/setter.
	//setExpireDate(): If the expiration date that is being set is smaller than the release date then
	//an error value of 0 should be set instead. (Or some other default value)
	//All the getter/setter methods of type String should follow the same rules as the comments mention
	//in CustomerTest.java
}
