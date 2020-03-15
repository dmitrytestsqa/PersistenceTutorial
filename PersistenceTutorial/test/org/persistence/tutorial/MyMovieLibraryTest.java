package org.persistence.tutorial;

import java.util.ArrayList;

import junit.framework.TestCase;

public class MyMovieLibraryTest extends TestCase {

	private Movie mov1;
	private Movie mov2;
	private Person pers1;
	private Person pers2;
	private MyMovieLibrary movLib;
	
	public void addItems() {
		movLib.addPerson(pers1);
		movLib.addPerson(pers2);
		movLib.addMovie(mov1);
		movLib.addMovie(mov2);
	}
	
	private void setup() {
		movLib = new MyMovieLibrary();
		mov1 = new Movie("Forest Gump", "Drama");
		mov2 = new Movie("Inception", "Action");
		pers1 = new Person();
		pers1.setName("Anna");
		pers2 = new Person();
		pers2.setName("Den");
	}

	public void testMyMovieLibrary() {
		setup();
		assertTrue(movLib.getMovies() instanceof ArrayList);
		assertTrue(movLib.getPeople() instanceof ArrayList);
		
		assertEquals(0, movLib.getMovies().size());
		assertEquals(0, movLib.getPeople().size());
	}

	public void testGetTitle() {
		MyMovieLibrary myMovieLib = new MyMovieLibrary();
		Movie movie = new Movie("Whiplash", "Drama");
		assertEquals(0, myMovieLib.getMovies().size());
		myMovieLib.addMovie(movie);
		assertEquals(1, myMovieLib.getMovies().size());
		assertEquals("Whiplash", myMovieLib.getTitle(movie));
	}

	public void testGetGenre() {
		MyMovieLibrary myMovieLib = new MyMovieLibrary();
		Movie movie = new Movie("The Lion King", "Animation");
		assertEquals(0, myMovieLib.getMovies().size());
		myMovieLib.addMovie(movie);
		assertEquals(1, myMovieLib.getMovies().size());
		assertEquals("Animation", myMovieLib.getGenre(movie));
		myMovieLib.getMovies().get(0).setGenre("Adventure");
		assertEquals("Adventure", myMovieLib.getGenre(movie));
	}

	public void testGetDirector() {
		MyMovieLibrary myMovieLib = new MyMovieLibrary();
		Movie movie = new Movie("The Prestige", "Mystery");
		assertEquals(0, myMovieLib.getMovies().size());
		myMovieLib.addMovie(movie);
		assertEquals(1, myMovieLib.getMovies().size());
		assertEquals("unknown director", myMovieLib.getDirector(movie));
		myMovieLib.getMovies().get(0).setDirector("Christopher Nolan");
		assertEquals("Christopher Nolan", myMovieLib.getDirector(movie));
	}

	public void testAddMovie() {
		MyMovieLibrary movLib = new MyMovieLibrary();
		Movie m1 = new Movie("Cidade de Deus", "Crime");
		movLib.addMovie(m1);
		assertEquals("Cidade de Deus", movLib.getMovies().get(0).getTitle());
	}

	public void testRemoveMovies(){
		setup();
		addItems();
		movLib.removeMovie(mov1);
		assertEquals(1, movLib.getMovies().size());
		assertEquals(0, movLib.getMovies().indexOf(mov2));
		
		movLib.removeMovie(mov2);
		assertEquals(0, movLib.getMovies().size());
		
	}

	public void testGetMovie() {
		MyMovieLibrary movLib = new MyMovieLibrary();
		Movie m1 = new Movie("Saving Private Ryan", "War");
		movLib.addMovie(m1);
		assertEquals("Saving Private Ryan", movLib.getMovies().get(0).getTitle());
		assertEquals("War", movLib.getMovies().get(0).getGenre());
	}

	public void testAddPerson() {
		setup();
		addItems();
		Person p3 = new Person();
		p3.setName("Jack");
		movLib.addPerson(p3);
		assertEquals(3, movLib.getPeople().size());
		assertEquals("Jack", movLib.getPeople().get(2).getName());
	}

	public void testGetPeople() {
		setup();
		addItems();
		Person p4 = new Person();
		p4.setName("Bob");
		movLib.addPerson(p4);
		assertEquals(3, movLib.getPeople().size());
		assertEquals("Bob", movLib.getPeople().get(2).getName());
	}

	public void testCheckOutMovie() {
		setup();
		addItems();
	
		assertTrue("Book did not check out correctly", movLib.checkOut(mov1, pers1));
		assertEquals("Anna", movLib.getMovies().get(0).getPerson().getName());
		
		assertTrue("Book did not check out correctly", movLib.checkOut(mov2, pers2));
		assertEquals("Den", movLib.getPeople().get(1).getName());
		assertEquals("Inception", movLib.getTitle(mov2));
		assertEquals("Action", movLib.getGenre(mov2));
		assertEquals("unknown director", movLib.getDirector(mov2));
		mov2.setDirector("Christopher Nolan");
		assertEquals("Christopher Nolan", movLib.getDirector(mov2));
		
	}

	public void testMovieShouldNotBeCheckedOutTwice(){
		setup();
		addItems();
		movLib.checkOut(mov1, pers1);

		assertFalse("Book was alread checked out", movLib.checkOut(mov1, pers1));
	}

	public void testCheckInMovie(){
		setup();
		addItems();
		movLib.checkOut(mov1, pers1);
		
		assertTrue("Book check in failed", movLib.checkIn(mov1));
	}

	public void testMovieShouldNotBeCheckedInTwice(){
		setup();
		addItems();
		movLib.checkOut(mov1, pers1);
		movLib.checkIn(mov1);
		assertFalse("Book was already checked in", movLib.checkIn(mov1));
	}

	public void testMovieShouldNotBeChekedInIfItWasNeverCheckedOut(){
		setup();
		addItems();
		
		assertFalse("Book was never checked out", movLib.checkIn(mov2));
	}

	public void testPersonShouldNotExceedMaximumMoviesLimit(){
		setup();
		addItems();
		pers1.setMaximumBooks(1);
		movLib.checkOut(mov1, pers1);
		
		assertFalse("Second book should not have checked out", movLib.checkOut(mov2, pers1));
	}

	public void testGetMoviesForPerson() {
		setup();
		addItems();
		assertEquals(0, movLib.getMoviesForPerson(pers1).size());
		
		movLib.checkOut(mov1, pers1);
		ArrayList<Movie> testBooks = movLib.getMoviesForPerson(pers1);
		assertEquals(1, testBooks.size());
		assertEquals(0, testBooks.indexOf(mov1));
		
		movLib.checkOut(mov1, pers1);
		testBooks = movLib.getMoviesForPerson(pers1);
	}

	public void testGetAvailableMovies() {
		setup();
		addItems();
				
		ArrayList<Movie> testList = movLib.getAvailableMovies();
		assertEquals(2,testList.size());
		assertEquals(0, testList.indexOf(mov1));
		assertEquals(1, testList.indexOf(mov2));
		
		movLib.checkOut(mov1, pers1);
		testList = movLib.getAvailableMovies();
		assertEquals(1, testList.size());
		
		assertEquals("Anna", movLib.getMovies().get(0).getPerson().getName());
		
		movLib.checkOut(mov2, pers1);
		testList = movLib.getAvailableMovies();
		assertEquals(1, testList.size());
	}

	public void testGetUnavailableMovies() {
		setup();
		addItems();
		
		ArrayList<Movie> testList = movLib.getUnavailableMovies();
		assertEquals(0,testList.size());
		
		movLib.checkOut(mov1, pers1);
		testList = movLib.getUnavailableMovies();
		assertEquals(1,testList.size());
		assertEquals(0, testList.indexOf(mov1));
		
		movLib.checkOut(mov2, pers1);
		testList = movLib.getUnavailableMovies();
		assertEquals(1,testList.size());
		assertEquals(0, testList.indexOf(mov1));
	}

	public void testGetPersonWithMovie() {
		setup();
		addItems();
		movLib.checkOut(mov1, pers1);
		assertEquals("Anna", movLib.getPersonWithMovie(mov1).get(0).getName());
		movLib.checkOut(mov2, pers2);
		assertEquals("Den", movLib.getPersonWithMovie(mov2).get(1).getName());
			
	}
	
	public void testToString() {
		setup();
		addItems();
		assertEquals("My Movie Library: 2 movies; 2 people.", movLib.toString());
	}
		
}