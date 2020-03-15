package org.persistence.tutorial;

import java.util.ArrayList;


public class MyLibrary {

	String name = "Test";
	ArrayList<Book> books;
	ArrayList<Person> people;

	public MyLibrary(String name) {
		this.name = name;
		books = new ArrayList<Book>();
		people = new ArrayList<Person>();
	}
	
	//the second constructor which have been added cause MyUtilitiesTest.convertToXml() test
	public MyLibrary() {
		books = new ArrayList<Book>();
		people = new ArrayList<Person>();
	}

	public String getName() {
		return name;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public ArrayList<Person> getPeople() {
		return people;
	}

	public void addBook(Book book) {
		this.books.add(book);
	}

	public void removeBook(Book book) {
		this.books.remove(book);
	}
	
	public void addPerson(Person person) {
		this.people.add(person);
	}
	
	public void removePerson(Person person) {
		this.people.remove(person);
	}

	public boolean checkOut(Book book, Person person) {
		int booksOut = this.getBooksForPerson(person).size();
		if ((book.getPerson() == null) &&
				(booksOut < person.getMaximumBooks()))
		{
			book.setPerson(person);
			return true;
		}
		else {
			return false;
		}
	}

	public boolean checkIn(Book book) {
		if (book.getPerson() != null) {
			book.setPerson(null);
			return true;
		}
		else {
			return false;
		}
	}

	public ArrayList<Book> getBooksForPerson(Person person) {
		ArrayList<Book> result = new ArrayList<Book>();
		for (Book aBook : this.getBooks()) {
			if ((aBook.getPerson() != null) &&
					(aBook.getPerson().getName() == person.getName()))
			{
				result.add(aBook);
			}
			
		}
		return result;
	}

	public ArrayList<Book> getAvailableBooks() {
		ArrayList<Book> result = new ArrayList<Book>();
		for (Book book : this.getBooks()) {
			if (book.getPerson() == null) {
				result.add(book);
			}
		}
		return result;
	}

	public ArrayList<Book> getUnavailableBooks() {
		ArrayList<Book> result = new ArrayList<Book>();
		for (Book book : this.getBooks()) {
			if (book.getPerson() != null) {
				result.add(book);
			}
		}
		
		return result;
	}

	public static void main(String[] args) {
		MyLibrary testLibrary = new MyLibrary("Test Drive Library");
		Book b1 = new Book("War and Peace");
		b1.setAuthor("Tolstoy");
		Book b2 = new Book("Great Expectations");
		b2.setAuthor("Charles Dickens");
		
		Person jim = new Person();
		jim.setName("Jim");
		Person sue = new Person();
		sue.setName("Sue");
		
		testLibrary.addBook(b1);
		testLibrary.addBook(b2);
		testLibrary.addPerson(jim);
		testLibrary.addPerson(sue);
		
		System.out.println("Just Created New Library");
		testLibrary.printStatus();
		
		testLibrary.checkOut(b1, sue);
		System.out.println("Checked out War and Peace to Sue");
		testLibrary.printStatus();
		
		testLibrary.checkIn(b1);
		testLibrary.checkOut(b2, jim);
		
		testLibrary.printStatus();

	}

	private void printStatus() {
		System.out.println("Status Report of MyLibrary: \n" + 
				this.toString());

		for (Book thisBook : this.getBooks()) {
			System.out.println(thisBook);
		}
		
		for (Person thisPerson : this.getPeople()) {
			int count = this.getBooksForPerson(thisPerson).size();
			System.out.println(thisPerson + " (has " + count + 
					" of my books)");
		}
		
		System.out.println("Books Available: "
				+ this.getAvailableBooks().size());
		System.out.println("--- End of Status Report---\n");
		
		
	}
	
	@Override
	public String toString() {
		return this.getName() + 
		": " + this.getBooks().size() + " books; " 
		+ this.getPeople().size() + " people.";
	}
		
}