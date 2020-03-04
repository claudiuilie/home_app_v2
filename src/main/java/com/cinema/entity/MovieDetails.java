package com.cinema.entity;

import java.util.ArrayList;

public class MovieDetails {
    private String snippet;
    private String formattedUrl;
    private Pagemap pagemap;
    private String title;

    public String getTitle() {
        return title;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getFormattedUrl() {
        return formattedUrl;
    }

    public String getThumbnailSrc() {
        return pagemap.cse_image.get(0).src;
    }

    public String getRatingValue() {
        return pagemap.aggregaterating.get(0).ratingvalue;
    }

    public String getReviewCount() {
        return pagemap.aggregaterating.get(0).reviewcount;
    }
    public String getRatingCount() {
        return pagemap.aggregaterating.get(0).ratingcount;
    }

    private static class Pagemap{
        ArrayList<Thumbnail> cse_image;
        ArrayList<Rating> aggregaterating;
    }

    public static class Thumbnail {
        String src;
    }

    public static class Rating {
        String ratingvalue;
        String reviewcount;
        String ratingcount;
    }

}
