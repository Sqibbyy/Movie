package sg.edu.rp.c346.id21005739.movie;

import java.io.Serializable;

public class Movie implements Serializable {
    private int _id;
    private String title;
    private String genre;
    private int year;
    private String rating;

    public Movie(int _id, String title, String genre, int year, String rating) {
        this._id = _id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public int get_id(){
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
