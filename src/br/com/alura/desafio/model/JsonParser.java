package br.com.alura.desafio.model;

import java.util.List;

public interface JsonParser {
	
	public List<? extends Content> parse();

}
