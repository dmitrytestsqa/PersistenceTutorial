package org.persistence.tutorial;

import java.util.ArrayList;


public class MyMovieLibrary {
	
	public String name = "";
	ArrayList<Movie> movies;
	ArrayList<Person> people;

	public MyMovieLibrary() {
	movies = new ArrayList<Movie>();
	people = new ArrayList<Person>();
	}

	public String getTitle (Movie movie) {
		return movie.getTitle();
	}

	public String getGenre (Movie movie) {
		return movie.getGenre();
	}

	public String getDirector (Movie movie) {
		return movie.getDirector();
	}

	public void addMovie(Movie movie) {
		this.movies.add(movie);
	}

	public void removeMovie(Movie movie) {
		this.movies.remove(movie);
		
	}

	public ArrayList<Movie> getMovies() {
		return this.movies;
	}

	public void addPerson(Person person) {
		this.people.add(person);
	}

	public ArrayList<Person> getPeople() {
		return people;
	}

	public boolean checkOut(Movie movie, Person person) {
		int moviesOut = this.getMoviesForPerson(person).size();
		if ((movie.getPerson() == null) &&
				(moviesOut < person.getMaximumMovies()))
		{
			movie.setPerson(person);
			return true;
		}
		else {
			return false;
		}
	}

	public boolean checkIn(Movie movie) {
		if (movie.getPerson() != null) {
			movie.setPerson(null);
			return true;
		}
		else {
			return false;
		}
	}

	public ArrayList<Movie> getMoviesForPerson(Person person) {
		ArrayList<Movie> result = new ArrayList<Movie>();
		for (Movie aMovie : this.getMovies()) {
			if ((aMovie.getPerson() != null) &&
					(aMovie.getPerson().getName() == person.getName()))
			{
				result.add(aMovie);
			}
			
		}
		return result;
	}

	public ArrayList<Movie> getAvailableMovies() {
		ArrayList<Movie> result = new ArrayList<Movie>();
		for (Movie movie : this.getMovies()) {
			if (movie.getPerson() == null){
				result.add(movie);
			}
		}
		return result;
	}

	public ArrayList<Movie> getUnavailableMovies() {
		ArrayList<Movie> result = new ArrayList<Movie>();
		for (Movie movie : this.getMovies()) {
			if (movie.getPerson() != null) {
				result.add(movie);
			}
		}
		
		return result;
	}

	public ArrayList<Person> getPersonWithMovie(Movie movie) {
		ArrayList<Person> result = new ArrayList<Person>();
		for (Movie aMovie : this.getMovies()) {
			if ((aMovie.getPerson() != null)) {
				result.add(aMovie.getPerson());
			}
		}
		return result;
	}
			
	private void printStatus() {
		System.out.println("Status Report of MyMovieLibrary: \n" + 
				this.toString());

		for (Movie thisMovie : this.getMovies()) {
			System.out.println(thisMovie);
		}
		
		for (Person thisPerson : this.getPeople()) {
			int count = this.getMoviesForPerson(thisPerson).size();
			System.out.println(thisPerson + " (has " + count + 
					" of my movies)");
		}
		
		System.out.println("Movies Available: "
				+ this.getAvailableMovies().size());
		
		System.out.println(this.getMoviesByGenre("drama").size() + " of “drama” movies");
		System.out.println(this.getMoviesByGenre("comedy").size() + " of “comedy” movies");
		System.out.println(this.getMoviesByGenre("family").size() + " of “family” movies");
		
		System.out.println(this.getMoviesByDirector("Alfred Hitchcock").size() + " of movies directed by Alfred Hitchcock");
		System.out.println(this.getMoviesByDirector("David Yates").size() + " of movies directed by David Yates");
		System.out.println(this.getMoviesByDirector("Steven Spielberg").size() + " of movies directed by Steven Spielberg");
		
		System.out.println("--- End of Status Report---\n");
		
	}
	
	@Override
	public String toString() {
		return "My Movie Library" + 
		": " + this.getMovies().size() + " movies; " 
		+ this.getPeople().size() + " people.";
	}
	
	public ArrayList<Movie> getMoviesByGenre(String genre) {
		ArrayList<Movie> moviesWithAppropriateGenre = new ArrayList<Movie>();
		for (Movie movie : movies) {
			if(movie.getGenre().equals(genre)) {
				moviesWithAppropriateGenre.add(movie);
			}
		}
		return moviesWithAppropriateGenre;
	}
	
	public ArrayList<Movie> getMoviesByDirector(String director) {
		ArrayList<Movie> moviesWithAppropriateDirector = new ArrayList<Movie>();
		for (Movie movie : movies) {
			if(movie.getDirector().equals(director)) {
				moviesWithAppropriateDirector.add(movie);
			}
		}
		return moviesWithAppropriateDirector;
	}
	
	
	public static void main (String[] args) {

		MyMovieLibrary newMyLibrary = MyMovieUtilities.getMyMovieLibraryFromXMLFile("cnxmovielibrary.xml");
		newMyLibrary.printStatus();
		
	}
			
}