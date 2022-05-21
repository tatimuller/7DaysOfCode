import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class RequestImdb {
	
	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
		
		HttpClient httpClient = HttpClient.newHttpClient();
		
		HttpRequest httpRequest = HttpRequest.newBuilder(new URI("https://imdb-api.com/en/API/Top250Movies/<apiKey>https://imdb-api.com/en/API/Top250Movies/"))
				.GET().build();
			
		
		HttpResponse<String> resp = httpClient.send(httpRequest, BodyHandlers.ofString());
		
		System.out.println(resp.body());
		
	}


}
