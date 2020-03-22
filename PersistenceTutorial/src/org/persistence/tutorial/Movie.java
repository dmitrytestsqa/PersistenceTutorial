package org.persistence.tutorial;

public class Movie {

	private String title;
	private String genre;
	private String director = "unknown director";
	Person person;

	public Movie(String title, String genre) {
		this.title = title;
		this.genre = genre;
	}
	
	public Movie() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String toString() {
		
		if (this.getPerson() == null) {
			return title + " directed by " + director + "; " + "The genre of this movie is " + genre + "; " + "Available.";
		} 
		else {
			return title + " directed by " + director + "; " + "The genre of this movie is " + genre + "; " + "Checked out to " + person.getName() + ".";
		}
		
	}
	
}
