package org.persistence.tutorial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class MyMovieUtilitiesTest {

	
	public MyMovieLibrary createMyMovieLibrary() {
		Movie m1;
		Movie m2;
		Person p1;
		Person p2;
		MyMovieLibrary ml;
		 
		m1 = new Movie("The Godfather", "Crime");
		m2 = new Movie("The Dark Knight", "Action");
		p1 = new Person();
		p1.setName("Fred");
		p2 = new Person();
		p2.setName("Sue");
		ml = new MyMovieLibrary();
		
		ml.addMovie(m1);
		ml.addMovie(m2);
		ml.addPerson(p1);
		ml.addPerson(p2);
		ml.checkOut(m1, p1);
		return ml;
	}

	
	
	@Test
	public void saveStringToFile() {
		
		String saveString = "this is test line one\n" + "this is test line two\n";
		
		File testFile = new File("testsavetostring.txt");
		testFile.delete();
		assertFalse("File shoul not exist", testFile.exists());
		
		assertTrue("File should have been saved", MyUtilities.saveStringToFile("testsavestring.txt", saveString));
		
		String newString = MyUtilities.getStringFromFile("testsavestring.txt");
		
		assertTrue("Save and get strings should be equal", saveString.equals(newString));
		
		//assertFalse("File should not be saved", MyUtilities.saveStringToFile("non-existent directory.thisshouldfail.txt", saveString));
		
		String emptyString = MyUtilities.getStringFromFile("badfilename.txt");
		assertTrue("String should be empty", emptyString.length() == 0);
	}
	
	
	@Test
	public void convertToXml() {
		MyMovieLibrary startMyMovieLibrary = createMyMovieLibrary();
		String testXMLOut = MyMovieUtilities.convertToXML(startMyMovieLibrary);
		MyMovieLibrary endMyMovieLibrary = MyMovieUtilities.convertFromXML(testXMLOut);
		
		assertEquals(2, endMyMovieLibrary.getMovies().size());
		assertEquals(2, endMyMovieLibrary.getPeople().size());
		assertEquals("Fred", endMyMovieLibrary.getMovies().get(0).getPerson().getName());
		
	}


	@Test
	public void saveToXMLFile() {
		MyMovieLibrary startMyMovieLibrary = createMyMovieLibrary();
		String fileName = "testmylibrary.xml";
		File testFile = new File(fileName);
		testFile.delete();
		assertFalse("File shoul not exist", testFile.exists());
		assertTrue("File should been saved", MyMovieUtilities.saveMyMovieLibraryToXMLFile(fileName, startMyMovieLibrary));
		
		MyMovieLibrary endMÛMoviesLibrary = MyMovieUtilities.getMyMovieLibraryFromXMLFile(fileName);
		assertEquals("The Godfather", endMÛMoviesLibrary.getMovies().get(0).getTitle());
		assertEquals(2, endMÛMoviesLibrary.getMovies().size());
		assertEquals(2, endMÛMoviesLibrary.getPeople().size());
		assertEquals("Fred", endMÛMoviesLibrary.getMovies().get(0).getPerson().getName());
	}
}
