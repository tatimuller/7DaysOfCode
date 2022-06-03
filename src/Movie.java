import java.util.Date;

public class Movie {

     private String title;
     private String year;
     private double rating;
     private String image;
	

	@Override
	public String toString() {
		return "["+"Title: "+ this.title + "Year: "+ this.year + "Rating: " + this.rating + "Image:"
					+ this.image +"]";
		
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
   
  
       
  
     
     
}