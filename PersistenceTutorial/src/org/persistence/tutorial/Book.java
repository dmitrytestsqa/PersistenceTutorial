package org.persistence.tutorial;

public class Book {

	public String title;
	public String author;
	private Person person;

	public Book(String title) {
		this.title = title;
		this.author = "unknown author";
	}
	
	//the second constructor which have been added cause MyUtilitiesTest.convertToXml() test
	public Book() {
		this.author = "unknown author";
	}
	
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setPerson(Person person) {
		this.person = person;
				
	}

	public Person getPerson() {
		return this.person;
	}
	
	@Override
	public String toString() {
		String available;
		if (this.getPerson() == null) {
			available = "Available";
		}
		else {
			available = "Checked out to " + 
			this.getPerson().getName();
		}
		return this.getTitle() + 
		" by " + this.getAuthor() +
		"; " + available;
		
	}

}
