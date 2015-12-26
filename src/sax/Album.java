package sax;


public class Album {
    
    private String title;
    private String artist;
    private String country;
    private String company;
    private int year;
    private double price;
    private String rating;
    
    public Album() {
        
    }
    
    public void setTilte(String title) { this.title = title; }
    public void setArtist(String artist) { this.artist = artist; }
    public void setCountry(String country) { this.country = country; }
    public void setCompany(String company) { this.company = company; }
    public void setYear(int year) { this.year = year; }
    public void setPrice(double price) { this.price = price; }
    public void setRating(String rating) { this.rating = rating; }
    
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getCountry() { return country; }
    public String getCompany() { return company; }
    public int getYear() { return year; }
    public double getPrice() { return price; }
    public String getRating() { return rating; }
    
    public void print() {
        System.out.println("Title: " + title);
        System.out.println("Artist: " + artist);
        System.out.println("Country: " + country);
        System.out.println("Company: "+ company);
        System.out.println("Year: " + year);
        System.out.println("Price: " + price);
        System.out.println("Rating: " + rating);   
    }
}
