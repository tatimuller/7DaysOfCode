package br.com.alura.desafio.main;
import java.io.PrintWriter;
 import java.util.List;

import br.com.alura.desafio.model.Movie;
import br.com.alura.desafio.util.HtmlGenerator;
import br.com.alura.desafio.util.ImdbApiClient;
import br.com.alura.desafio.util.ImdbMovieJsonParser;

public class RequestImdb {

	public static void main(String[] args) throws Exception {

		String apiKey = "";//informe sua chave de consulta na apiImdb
		
		ImdbApiClient iac = new ImdbApiClient();
		String json = iac.conectImdbApiClient(apiKey);
		
		List<Movie> movies = new ImdbMovieJsonParser(json).parse();
		
		PrintWriter writer = new PrintWriter("content.html");
		new HtmlGenerator(writer).generate(movies);
		writer.close();

	}
}


