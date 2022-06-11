package br.com.alura.desafio.util;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ImdbApiClient {

	public String conectImdbApiClient(String apiKey) throws IOException, InterruptedException {
		

		HttpClient httpClient = HttpClient.newHttpClient();
		URI uriImdb = URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey);
		HttpRequest httpRequest = HttpRequest.newBuilder().uri(uriImdb).build();
		HttpResponse<String> resp = httpClient.send(httpRequest, BodyHandlers.ofString());

		return resp.body();

	}

}
