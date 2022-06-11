package br.com.alura.desafio.util;
import java.io.PrintWriter;
import java.util.List;

import br.com.alura.desafio.model.Movie;

public class HtmlGenerator {
	private String head;
	private String divTemplate;
	PrintWriter writerHtml;

	public HtmlGenerator(PrintWriter writer) {
		this.head = """
				<head>
					<meta charset=\"utf-8\">
					<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
					<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\"
						+ "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">
				</head>
				""";

		this.divTemplate = """
				<div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">
					<h4 class=\"card-header\">%s</h4>
					<div class=\"card-body\">
						<img class=\"card-img\" src=\"%s\" alt=\"%s\">
						<p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
					</div>
				</div>
				""";

		this.writerHtml = writer;

	}

	public void generate(List<Movie> movies) {

		this.writerHtml.print(this.head);

		for (Movie movie : movies) {

			writerHtml.print(criaDivTaplate(movie, divTemplate));
		}
	}

	private static String criaDivTaplate(Movie movie, String divTemplate) {

		return String.format(divTemplate, movie.title(), movie.urlImage(), movie.title(), movie.rating(),
				movie.year());

	}

}
