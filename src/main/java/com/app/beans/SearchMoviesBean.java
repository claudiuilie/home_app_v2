package com.app.beans;


import com.app.entity.Movie;
import com.app.helpers.FetchMovies;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.util.*;

@ManagedBean(name = "searchMovies")
@SessionScoped
public class SearchMoviesBean {

    private String input;
    private List<Movie> movieList = new LinkedList<>();
    private String movieThumbnail;
    private String movieLink;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList.clear();
        this.movieList = movieList;
    }

    public String getMovieThumbnail() {
        return movieThumbnail;
    }

    public void setMovieThumbnail(String movieThumbnail) {
        this.movieThumbnail = movieThumbnail;
    }

    public String getMovieLink() {
        return movieLink;
    }

    public void setMovieLink(String movieLink) {
        this.movieLink = movieLink;
    }

    public void searchMovies() throws IOException {

        setMovieList(FetchMovies.fetchMovies(this.input));


//        Movie movie1 = new Movie();
//        movie1.setSize("2gb");
//        movie1.setTitle("Mov 1 2080p [5.1]........................................");
//        movie1.setPeers("500");
//        movie1.setSeeders("0");
//        movie1.setUpload_date("20-02-2020");
//        movie1.setRating_value("7.8");
//        movie1.setImdb_url("https://www.imdb.com/title/tt8421350/");
//        movie1.setThumbnail("https://m.media-amazon.com/images/M/MV5BZGIxNDUzMDktYTg1ZC00OGRiLTliNWUtZmY4NTE0MDU0YWU4XkEyXkFqcGdeQXVyODUxOTU0OTg@._V1_UY1200_CR90,0,630,1200_AL_.jpg");
//
//        Movie movie2 = new Movie();
//        movie2.setSize("3gb");
//        movie2.setTitle("Mov 2");
//        movie2.setPeers("15");
//        movie2.setSeeders("16");
//        movie2.setUpload_date("21-02-2020");
//        movie2.setRating_value("5.8");
//        movie2.setOn_watchlist(true);
//
//        Movie movie3 = new Movie();
//        movie3.setSize("4gb");
//        movie3.setTitle("Mov 3");
//        movie3.setPeers("5");
//        movie3.setSeeders("5");
//        movie3.setUpload_date("21-02-2020");
//        movie3.setRating_value("9.8");
//
//        this.movieList.clear();
//        this.movieList.add(movie1);
//        this.movieList.add(movie2);
//        this.movieList.add(movie3);

        if(this.movieList.size() > 0){
            setMovieThumbnail(this.movieList.get(0).getThumbnail());
            setMovieLink(this.movieList.get(0).getImdb_url());
        }
        System.out.println(this.movieList);
    }

}