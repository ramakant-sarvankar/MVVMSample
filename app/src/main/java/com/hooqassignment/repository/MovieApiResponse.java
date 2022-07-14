package com.hooqassignment.repository;

import com.google.gson.annotations.SerializedName;
import com.hooqassignment.Constants;
import com.hooqassignment.repository.model.MovieModel;

import java.util.List;

public class MovieApiResponse {
    @SerializedName("results")
    private List<MovieModel> movieList;

    @SerializedName("total_pages")
    private int pageCount;

    public List<MovieModel> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieModel> movieList) {
        this.movieList = movieList;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public boolean isResponseSuccessfull() {
        return isResponseSuccessfull;
    }

    public void setResponseStatus(boolean isResponseSuccessfull) {
        this.isResponseSuccessfull = isResponseSuccessfull;
    }

    private boolean isResponseSuccessfull;

    public Constants.RESPONSE_STATUS responseStatus;
}
