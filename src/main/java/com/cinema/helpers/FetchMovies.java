package com.cinema.helpers;

import com.cinema.entity.Movie;
import com.cinema.resources.TrackerConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/*
*@author Claudiu Ilie
* Helper for fetching movies info.
*/

public class FetchMovies  {

    public static List<Movie> fetchMovies(String input) throws IOException {

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

        // create movie details object that containts title,thumbnail, details etc..

//        MovieDetails movieDetails = GoogleSearch.fetchMovieDetails(input);

        if(body.size() > 0){
            String[] sizeAndUploadDate = null;
            Elements elements = body.first().getElementsByTag("tr");
            elements.remove(elements.size() - 1);
            for(Element e : elements){
                Movie movie = new Movie();
                movie.setTitle(e.getElementsByClass("detName").text());
                movie.setSeeders(e.getElementsByAttribute("align").first().text());
                movie.setPeers(e.getElementsByAttribute("align").last().text());
                movie.setMagnetLink(e.getElementsByAttributeValue("title","Download this torrent using magnet").attr("href"));
                sizeAndUploadDate = e.getElementsByTag("font").text().split(",");
                movie.setSize(sizeAndUploadDate[1]);
                movie.setUploadDate(sizeAndUploadDate[0]);
//                movie.setPoster(movieDetails.getThumbnailSrc());
                movie.setPoster("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWW04_Gt3YCljUX9LCTcTJTjZP8Ebeh_Xx75QRU1pzLMZxtVnXZDUhq3M");

                System.out.println(movie);
                movies.add(movie);
            }
        }

        return movies;
    }
}
