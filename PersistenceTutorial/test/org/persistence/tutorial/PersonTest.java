package org.persistence.tutorial;

import junit.framework.TestCase;

public class PersonTest extends TestCase {

	public void testPerson() {
		Person p = new Person();
		assertEquals("unknown name", p.getName());
		assertEquals(3, p.getMaximumBooks());
		assertEquals(1, p.getMaximumMovies());
	}

	public void testSetMaximumBooks() {
		Person p1 = new Person();
		p1.setMaximumBooks(10);
		assertEquals(10, p1.getMaximumBooks());
	}

	public void testGetMaximumMovies() {
		Person p = new Person();
		p.setMaximumMovies(10);
		assertEquals(10, p.getMaximumMovies());
	}

	public void testGetMaximumBooks() {
		Person p = new Person();
		p.setMaximumBooks(9);
		assertEquals(9, p.getMaximumBooks());
	}

	public void testSetMaximumMovies() {
		Person p1 = new Person();
		assertEquals(1, p1.getMaximumMovies());
		p1.setMaximumMovies(100);
		assertEquals(100, p1.getMaximumMovies());
	}

	public void testSetName() {
		Person p1 = new Person();
		p1.setName("Fred Flintstone");
		
		assertEquals("Fred Flintstone", p1.getName());
	}

	public void testGetName() {
		Person p1 = new Person();
		p1.setName("Freddie Mercury");
		
		assertEquals("Freddie Mercury", p1.getName());
	}

	public void testToString() {
		Person p1 = new Person();
		p1.setName("Fred Flintstone");
		String testString = "Fred Flintstone (3 books, 1 movies)";
		
		assertEquals(testString, p1.toString());
	}

}