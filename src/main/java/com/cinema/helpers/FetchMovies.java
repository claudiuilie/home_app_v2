package com.cinema.helpers;

import com.cinema.entity.MoviesList;
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

    public static Set<MoviesList> fetchMovies(String input) throws IOException {

        URL url = new URL(TrackerConfig.BASE_URL + input);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", TrackerConfig.USER_AGENT);
        connection.setRequestMethod("GET");
        String content = String.valueOf(HttpResponseToString.parseContent(connection));

        return buildMovieList(content, input);
    }

    private static Set<MoviesList>  buildMovieList(String content, String input) throws IOException {

        Document document = Jsoup.parse(String.valueOf(content));
        Elements body = document.getElementsByTag("tbody");
        Set<MoviesList> movies = new LinkedHashSet<>();

        // create movie details object that containts title,thumbnail, details etc..

//        MovieDetails movieDetails = GoogleSearch.fetchMovieDetails(input);

        if(body.size() > 0){
            String[] sizeAndUploadDate = null;
            Elements elements = body.first().getElementsByTag("tr");
            elements.remove(elements.size() - 1);
            for(Element e : elements){
                MoviesList moviesList = new MoviesList();
                moviesList.setTitle(e.getElementsByClass("detName").text());
                moviesList.setSeeders(e.getElementsByAttribute("align").first().text());
                moviesList.setPeers(e.getElementsByAttribute("align").last().text());
                moviesList.setMagnetLink(e.getElementsByAttributeValue("title","Download this torrent using magnet").attr("href"));
                sizeAndUploadDate = e.getElementsByTag("font").text().split(",");
                moviesList.setSize(sizeAndUploadDate[1]);
                moviesList.setUploadDate(sizeAndUploadDate[0]);
//                movie.setPoster(movieDetails.getThumbnailSrc());
                moviesList.setPoster("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWW04_Gt3YCljUX9LCTcTJTjZP8Ebeh_Xx75QRU1pzLMZxtVnXZDUhq3M");

                System.out.println(moviesList);
                movies.add(moviesList);
            }
        }

        return movies;
    }
}
