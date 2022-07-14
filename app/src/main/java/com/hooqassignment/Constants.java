package com.hooqassignment;

import com.hooqassignment.repository.remote.ApiRepository;

public class Constants {
    public static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    public static final String LANGUAGE = "en-US";
    public static final String API_KEY = "3dfe17fae17f71def6106b6a6b233163";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    public static final String IMAGE_SIZE = "w300";
    public static final String BACKTROP_IMAGE_SIZE = "w780";

    //api keys
    public static final String KEY_API_KEY = "api_key";
    public static final String KEY_LANGUAGE = "language";
    public static final String KEY_PAGE = "page";

    public enum RESPONSE_STATUS{
        SUCCESS,NO_INTERNET,SERVER_FAILURE
    }

}
