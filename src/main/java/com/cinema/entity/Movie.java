package com.cinema.entity;

public class Movie {

    private String title;
    private String size;
    private String seeders;
    private String peers;
    private String uploadDate;
    private String poster;
    private String magnetLink;
    private boolean onDisk;
    private String imdbUrl;
    private String thumbnail;
    private String description;
    private String ratingValue;
    private String ratingCount;
    private String reviewCount;

    public String getMagnetLink() {
        return magnetLink;
    }

    public void setMagnetLink(String magnetLink) {
        this.magnetLink = magnetLink;
    }

    public String getImdbUrl() {
        return imdbUrl;
    }

    public void setImdbUrl(String imdbUrl) {
        this.imdbUrl = imdbUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSeeders() {
        return seeders;
    }

    public void setSeeders(String seeders) {
        this.seeders = seeders;
    }

    public String getPeers() {
        return peers;
    }

    public void setPeers(String peers) {
        this.peers = peers;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public boolean isOnDisk() {
        return onDisk;
    }

    public void setOnDisk(boolean onDisk) {
        this.onDisk = onDisk;
    }

    public void downloadMovie() {

// method to download
    }

    public void deleteMovie() {
//method to delete
    }

    public void findOnDisk() {
        //find on disk
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", size='" + size + '\'' +
                ", seeders='" + seeders + '\'' +
                ", peers='" + peers + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", poster='" + poster + '\'' +
                ", magnetLink='" + magnetLink + '\'' +
                ", onDisk=" + onDisk +
                ", imdbUrl='" + imdbUrl + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", description='" + description + '\'' +
                ", ratingValue='" + ratingValue + '\'' +
                ", ratingCount='" + ratingCount + '\'' +
                ", reviewCount='" + reviewCount + '\'' +
                '}';
    }
}
