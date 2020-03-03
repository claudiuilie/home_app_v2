package com.cinema.helpers;

import com.cinema.entity.MovieDetails;
import com.cinema.resources.GoogleApiConfig;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.net.*;

public class GoogleSearch {

    public static MovieDetails fetchMovieDetails(String movieTitle) throws IOException {

        String key = String.format("key=%s&",URLEncoder.encode(GoogleApiConfig.API_KEY, "UTF-8"));
        String engineId = String.format("cx=%s&",URLEncoder.encode(GoogleApiConfig.SEARCH_ENGINE_ID, "UTF-8"));
        String q = String.format("q=%s",URLEncoder.encode(movieTitle, "UTF-8"));
        URL url = new URL(GoogleApiConfig.BASE_URL+key+engineId+q);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", GoogleApiConfig.USER_AGENT);
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        MovieDetails movieDetails = null;

        if(responseCode != 200)
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        else
        {
            JSONObject movieDetailsItems;
            JSONObject movieDetailsObject =  parseContent(connection);
            if(movieDetailsObject != null && movieDetailsObject.containsKey("items")){

                Gson gson = new Gson();
                JSONArray movieDetailsArray = (JSONArray) movieDetailsObject.get("items");
                movieDetailsItems = (JSONObject) movieDetailsArray.get(0);

                movieDetails = gson.fromJson(String.valueOf(movieDetailsItems), MovieDetails.class);

            }

        }

        return movieDetails;

    }

    private static JSONObject parseContent(HttpURLConnection connection) throws IOException {

        String content = HttpResponseToString.parseContent(connection);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject)parser.parse(String.valueOf(content));
        } catch (ParseException e) {
            System.out.println(e.toString());
        }

        return jsonObject;
    }

}
