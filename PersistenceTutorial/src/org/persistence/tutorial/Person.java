package org.persistence.tutorial;

public class Person {
	
	private String name; // person's name
	private int maximumBooks; // # books can check out
	private int maximumMovies = 1;

	public Person() {
		name = "unknown name";
		maximumBooks = 3;
	}

	public Person(String name, int maximumBooks) {
		super();
		this.name = name;
		this.maximumBooks = maximumBooks;
	}

	public int getMaximumBooks() {
		return maximumBooks;
	}

	public int getMaximumMovies() {
		return maximumMovies;
	}

	public String getName() {
		return name;
	}

	public void setMaximumBooks(int maximumBooks) {
		this.maximumBooks = maximumBooks;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public String toString() {
		return this.getName() + " (" + this.getMaximumBooks()
		+ " books, " + maximumMovies + " movies)";
	}

	public void setMaximumMovies(int lim) {
		this.maximumMovies = lim;
	}

}
