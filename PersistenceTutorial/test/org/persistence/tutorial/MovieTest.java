package org.persistence.tutorial;

import junit.framework.TestCase;

public class MovieTest extends TestCase {

public void testMovie() {
	Movie m1 = new Movie("Matrix", "Action");
	assertEquals("Matrix", m1.getTitle());
	assertEquals("Action", m1.getGenre());
	assertEquals("unknown director", m1.getDirector());
	}

public void testGetTitle() {
	Movie m1 = new Movie("Goodfellas", "Crime");
	assertEquals("Goodfellas", m1.getTitle());
}

public void testGetGenre() {
	Movie m1 = new Movie("Joker", "Drama");
	assertEquals("Drama", m1.getGenre());
}

public void testGetDirector() {
	Movie m1 = new Movie("Joker", "Drama");
	assertEquals("unknown director", m1.getDirector());
}

public void testSetTitle() {
	Movie m1 = new Movie("Matrix", "Action");
	m1.setTitle("Interstellar");
	assertEquals("Interstellar", m1.getTitle());
}

public void testSetGenre() {
	Movie m1 = new Movie("Goodfellas", "Crime");
	m1.setGenre("Drama");
	assertEquals("Drama", m1.getGenre());
}

public void testSetDirector() {
	Movie m1 = new Movie("Goodfellas", "Crime");
	m1.setDirector("Martin Scorsese");
	assertEquals("Martin Scorsese", m1.getDirector());
}

public void testSetPerson() {
	Movie m1 = new Movie("Joker", "Drama");
	Person p1 = new Person();
	p1.setName("Daria");
	m1.setPerson(p1);
	
	assertTrue(m1.getPerson() instanceof Person);
	assertEquals("Daria", m1.getPerson().getName());
}

public void testGetPerson() {
	Movie m1 = new Movie("Matrix", "Action");
	Person p1 = new Person();
	m1.setPerson(p1);
	
	assertTrue(m1.getPerson() instanceof Person);
	assertEquals("unknown name", m1.getPerson().getName());
}

public void testToString() {
	Movie m1 = new Movie("Goodfellas", "Crime");
	assertEquals("Goodfellas directed by unknown director; The genre of this movie is Crime; Available.", m1.toString());
	
	Person p1 = new Person();
	m1.setPerson(p1);
	p1.setName("Arnold");
	assertEquals("Goodfellas directed by unknown director; The genre of this movie is Crime; Checked out to Arnold.", m1.toString());
}
	
}
