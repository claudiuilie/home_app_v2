package com.cinema.beans;

import com.cinema.entity.MoviesList;
import com.cinema.helpers.FetchMovies;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.util.*;

@ManagedBean(name = "cinema")
@RequestScoped
public class CinemaBean {

    private String input;
    private Set<MoviesList> movieList = new LinkedHashSet<>();

    public void searchMovies() throws IOException {

        System.out.println("Test"+ this.input);
        this.movieList = FetchMovies.fetchMovies(this.input);
        System.out.println(this.movieList);
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public Set<MoviesList> getMovieList() {
        return movieList;
    }
}