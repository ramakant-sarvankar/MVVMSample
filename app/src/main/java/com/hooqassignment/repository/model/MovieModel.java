package com.hooqassignment.repository.model;

import com.google.gson.annotations.SerializedName;
import com.hooqassignment.repository.remote.ApiRepository;

import java.io.Serializable;

public class MovieModel implements Serializable {
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("adult")
    private String adult;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("id")
    private int id;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("title")
    private String title;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("popularity")
    private String popularity;
    @SerializedName("vote_count")
    private long voteCount;
    @SerializedName("video")
    private String video;
    @SerializedName("vote_average")
    private String voteAverage;

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getPopularity() {
        return popularity;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public String getVideo() {
        return video;
    }

    public String getVoteAverage() {
        return voteAverage;
    }


    @Override
    public String toString() {
        return "name>"+getOriginalTitle()+",movieId>"+getId();
    }
}
