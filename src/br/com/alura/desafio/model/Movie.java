package br.com.alura.desafio.model;


public class Movie implements Content{

     private String title;
     private String year;
     private String rating;
     private String image;
     
     
 	@Override
 	public String title() {
 		return this.title;
 	}


 	@Override
 	public String urlImage() {
 		
 		return this.image;
 	}


 	@Override
 	public String rating() {
 		
 		return this.rating;
 	}


 	@Override
 	public String year() {
 		return this.year;
 	}
    
	

	@Override
	public String toString() {
		return "["+"Title: "+ this.title + "Year: "+ this.year + "Rating: " + this.rating + "Image:"
					+ this.image +"]";
		
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}


	public void setImage(String image) {
		this.image = image;
	}


	

       
  
     
     
}