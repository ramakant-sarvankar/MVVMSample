package com.hooqassignment.repository.remote;

import com.hooqassignment.repository.MovieApiResponse;
import com.hooqassignment.repository.model.MovieModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiService {
    @GET("now_playing")
    Call<MovieApiResponse> getNowPlayingMovieList(@QueryMap Map<String, String> params);

    @GET("{movie_id}/similar")
    Call<MovieApiResponse> getSimilarMovieList(@Path("movie_id")  String movieId, @QueryMap Map<String, String> params );

}
