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
            movie.setSize(watchlistSet.getString("size"));
            movie.setSeeders(watchlistSet.getString("seeders"));
            movie.setPeers(watchlistSet.getString("peers"));
            movie.setUpload_date(watchlistSet.getString("upload_date"));
            movie.setMagnet_link(watchlistSet.getString("magnet_link"));
            movie.setImdb_url(watchlistSet.getString("imdb_url"));
            movie.setDescription(watchlistSet.getString("description"));
            movie.setRating_value(watchlistSet.getString("rating_value"));
            movie.setRating_count(watchlistSet.getString("rating_count"));
            movie.setReview_count(watchlistSet.getString("review_count"));
            movie.setThumbnail(watchlistSet.getString("thumbnail"));
            movie.setTorr_posted(watchlistSet.getInt("torr_posted"));
            movie.setTorr_paused(watchlistSet.getInt("torr_paused"));
            movie.setTorr_finish(watchlistSet.getInt("torr_finish"));
            movie.setTorr_error(watchlistSet.getInt("torr_error"));
            movie.setTorr_mb_s(watchlistSet.getInt("torr_mb_s"));
            movie.setTorr_mb_s(watchlistSet.getInt("on_disk"));
            movie.setTorr_mb_s(watchlistSet.getInt("on_watchlist"));

            watchlist.add(movie);
        }

        setWatchList(watchlist);
        System.out.println(this.watchList);
    }
}