package com.app.helpers.movies;

import com.app.entity.Movie;
import com.app.entity.MovieDetails;
import com.app.helpers.HttpResponseToString;
import com.app.helpers.SqlConnector;
import com.app.resources.TrackerConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/*
*@author Claudiu Ilie
* Helper for fetching movies info.
*/

public class FetchMovies  {

    public static List<Movie> fetchMovies(String input) throws IOException {
System.out.println(input);
        URL url = new URL(TrackerConfig.BASE_URL + input);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", TrackerConfig.USER_AGENT);
        connection.setRequestMethod("GET");
        String content = String.valueOf(HttpResponseToString.parseContent(connection));

        return buildMovieList(content, input);
    }

    private static List<Movie>  buildMovieList(String content, String input) throws IOException {

        Document document = Jsoup.parse(String.valueOf(content));
        Elements body = document.getElementsByTag("tbody");
        List<Movie> movies = new LinkedList<>();

        //select * from watchlist to set on_watchlist flag
        SqlConnector sqlConnector = new SqlConnector();
        ResultSet watchlistSet = sqlConnector.selectResults("SELECT title FROM watchlist");
        List<String> watchlistTitles = new ArrayList<>();

        while(true){
            try {
                if (!watchlistSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                watchlistTitles.add(watchlistSet.getString("title"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // create movie details object that containts title,thumbnail, details etc..
        MovieDetails movieDetails = GoogleSearch.fetchMovieDetails(input);

        if(body.size() > 0){
            String[] sizeAndUploadDate = null;
            Elements elements = body.first().getElementsByTag("tr");
            elements.remove(elements.size() - 1);
            for(Element e : elements){
                Movie movie = new Movie();
                movie.setTitle(e.getElementsByClass("detName").text());
                movie.setSeeders(e.getElementsByAttribute("align").first().text());
                movie.setPeers(e.getElementsByAttribute("align").last().text());
                movie.setMagnet_link(e.getElementsByAttributeValue("title","Download this torrent using magnet").attr("href"));
                sizeAndUploadDate = e.getElementsByTag("font").text().split(",");
                movie.setSize(sizeAndUploadDate[1].replace("Size ",""));
                movie.setUpload_date(sizeAndUploadDate[0].replace("Uploaded ",""));
                movie.setThumbnail(movieDetails.getThumbnailSrc());
                movie.setImdb_url(movieDetails.getFormattedUrl());
                movie.setRating_value(movieDetails.getRatingValue());
                movie.setRating_count(movieDetails.getRatingCount());
                movie.setReview_count(movieDetails.getReviewCount());
                movie.setDescription(movieDetails.getSnippet());
                movie.setOn_watchlist(watchlistTitles.contains(movie.getTitle()));
                movies.add(movie);
                System.out.println(movie);
            }
        }

        return movies;
    }
}
