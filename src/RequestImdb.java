import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestImdb {

	public static void main(String[] args) throws Exception {

		HttpClient httpClient = HttpClient.newHttpClient();

		HttpRequest httpRequest = HttpRequest.newBuilder(new URI("https://imdb-api.com/en/API/Top250Movies/k_rtnl3hgg"))
				.GET().build();

		HttpResponse<String> resp = httpClient.send(httpRequest, BodyHandlers.ofString());
		String json = resp.body();

		String[] moviesArray = parseJsonMovies(json);

		List<String> titles = parseTitles(moviesArray);
		List<String> urlImages = parseUrlImages(moviesArray);
		List<String> rating	= parseRating(moviesArray);
		List<String> date = parseDate(moviesArray);
		
		Movie movie;		
		List<Movie> movies = new LinkedList<Movie>();

		for (int i = 0; i < titles.size();  i++) {
			movie = new Movie();
			
			movie.setTitle(titles.get(i));
			movie.setImage(urlImages.get(i));			
			movie.setRating(Double.parseDouble(rating.get(i)));
			movie.setYear(date.get(i));
			movies.add(movie);
			
		}

		PrintWriter writer = new PrintWriter("content.html");

		new HtmlGenerator(writer).generate(movies, writer); //movies é um List<Movie>

		writer.close();
		
		
			

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
