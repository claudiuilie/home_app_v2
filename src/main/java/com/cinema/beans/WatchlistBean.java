package com.cinema.beans;

import com.cinema.entity.Movie;
import com.cinema.helpers.SqlConnector;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name="watchlist")
@RequestScoped
public class WatchlistBean {

    private List<Movie> watchList = new LinkedList<>();

    public List<Movie> getWatchList() throws SQLException {
        fetchWatchlist();
        return watchList;
    }

    public void setWatchList(List<Movie> watchList) {
        this.watchList = watchList;
    }

    public void fetchWatchlist() throws SQLException {

        List<Movie> watchlist = new LinkedList<>();
        Movie movie = null;

        SqlConnector sqlConnector = new SqlConnector();
        ResultSet watchlistSet = sqlConnector.selectResults("SELECT * FROM watchlist order by created desc");

        while(watchlistSet.next()){
            movie = new Movie();
            movie.setId(watchlistSet.getInt("id"));
            movie.setTitle(watchlistSet.getString("title"));
            movie.setThumbnail(watchlistSet.getString("thumbnail"));
            watchlist.add(movie);
        }

        setWatchList(watchlist);
        System.out.println(this.watchList);
    }
}


