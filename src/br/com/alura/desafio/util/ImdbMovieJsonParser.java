package br.com.alura.desafio.util;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.alura.desafio.model.JsonParser;
import br.com.alura.desafio.model.Movie;

public class ImdbMovieJsonParser implements JsonParser{

	private List<String> titles;
	private List<String> urlImages;
	private List<String> rating;
	private List<String> date;
	private String[] moviesArray;
	private Movie movie;
	List<Movie> movies = new ArrayList<Movie>();

	public ImdbMovieJsonParser(String json) {
		this.moviesArray = parseJsonMovies(json);
		this.titles = parseTitles(moviesArray);
		this.urlImages = parseUrlImages(moviesArray);
		this.rating = parseRating(moviesArray);
		this.date = parseDate(moviesArray);

	}

	public List<Movie> parse() {

		for (int i = 0; i < titles.size(); i++) {
			this.movie = new Movie();

			this.movie.setTitle(titles.get(i));
			this.movie.setImage(urlImages.get(i));
			this.movie.setRating((rating.get(i)));
			this.movie.setYear(date.get(i));
			this.movies.add(movie);

		}

		return movies;
	}

	private static String[] parseJsonMovies(String json) {
		Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);

		if (!matcher.matches()) {
			throw new IllegalArgumentException("no match in " + json);
		}

		String[] moviesArray = matcher.group(1).split("\\},\\{");
		moviesArray[0] = moviesArray[0].substring(1);
		int last = moviesArray.length - 1;
		String lastString = moviesArray[last];
		moviesArray[last] = lastString.substring(0, lastString.length() - 1);
		return moviesArray;
	}

	private static List<String> parseTitles(String[] moviesArray) {
		return parseAttribute(moviesArray, 3);
	}

	private static List<String> parseUrlImages(String[] moviesArray) {
		return parseAttribute(moviesArray, 5);
	}

	private static List<String> parseRating(String[] moviesArray) {
		return parseAttribute(moviesArray, 7);
	}

	private static List<String> parseDate(String[] moviesArray) {
		return parseAttribute(moviesArray, 4);
	}

	private static List<String> parseAttribute(String[] moviesArray, int pos) {
		return Stream.of(moviesArray).map(e -> e.split("\",\"")[pos]).map(e -> e.split(":\"")[1])
				.map(e -> e.replaceAll("\"", "")).collect(Collectors.toList());
	}
}
