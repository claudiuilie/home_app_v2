package com.cinema.entity;

public class Movie {
    private int id;
    private String title;
    private String size;
    private String seeders;
    private String peers;
    private String upload_date;
    private String magnet_link;
    private String imdb_url;
    private String thumbnail;
    private String description;
    private String rating_value;
    private String rating_count;
    private String review_count;
    private int torr_posted;
    private int torr_pause;
    private int torr_finish;
    private int torr_error;
    private int torr_mb_s;
    private boolean on_disk;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }

    public String getMagnet_link() {
        return magnet_link;
    }

    public void setMagnet_link(String magnet_link) {
        this.magnet_link = magnet_link;
    }

    public String getImdb_url() {
        return imdb_url;
    }

    public void setImdb_url(String imdb_url) {
        this.imdb_url = imdb_url;
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

    public String getRating_value() {
        return rating_value;
    }

    public void setRating_value(String rating_value) {
        this.rating_value = rating_value;
    }

    public String getRating_count() {
        return rating_count;
    }

    public void setRating_count(String rating_count) {
        this.rating_count = rating_count;
    }

    public String getReview_count() {
        return review_count;
    }

    public void setReview_count(String review_count) {
        this.review_count = review_count;
    }

    public int getTorr_posted() {
        return torr_posted;
    }

    public void setTorr_posted(int torr_posted) {
        this.torr_posted = torr_posted;
    }

    public int getTorr_pause() {
        return torr_pause;
    }

    public void setTorr_pause(int torr_pause) {
        this.torr_pause = torr_pause;
    }

    public int getTorr_finish() {
        return torr_finish;
    }

    public void setTorr_finish(int torr_finish) {
        this.torr_finish = torr_finish;
    }

    public int getTorr_error() {
        return torr_error;
    }

    public void setTorr_error(int torr_error) {
        this.torr_error = torr_error;
    }

    public int getTorr_mb_s() {
        return torr_mb_s;
    }

    public void setTorr_mb_s(int torr_mb_s) {
        this.torr_mb_s = torr_mb_s;
    }

    public boolean isOn_disk() {
        return on_disk;
    }

    public void setOn_disk(boolean on_disk) {
        this.on_disk = on_disk;
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
                "id=" + id +
                ", title='" + title + '\'' +
                ", size='" + size + '\'' +
                ", seeders='" + seeders + '\'' +
                ", peers='" + peers + '\'' +
                ", upload_date='" + upload_date + '\'' +
                ", magnet_link='" + magnet_link + '\'' +
                ", imdb_url='" + imdb_url + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", description='" + description + '\'' +
                ", rating_value='" + rating_value + '\'' +
                ", rating_count='" + rating_count + '\'' +
                ", review_count='" + review_count + '\'' +
                ", torr_posted=" + torr_posted +
                ", torr_pause=" + torr_pause +
                ", torr_finish=" + torr_finish +
                ", torr_error=" + torr_error +
                ", torr_mb_s=" + torr_mb_s +
                ", on_disk=" + on_disk +
                '}';
    }
}
