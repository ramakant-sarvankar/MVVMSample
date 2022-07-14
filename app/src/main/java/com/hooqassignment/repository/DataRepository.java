package com.hooqassignment.repository;


import androidx.lifecycle.MutableLiveData;

import com.hooqassignment.repository.model.MovieModel;
import com.hooqassignment.repository.remote.ApiRepository;

import java.util.List;

public class DataRepository {
    private static DataRepository instance;
    private MutableLiveData<List<MovieModel>> mMoviesList;
    private ApiRepository mApiRepository;

    private DataRepository(){
        mApiRepository = ApiRepository.getInstance();
    }

    public static DataRepository getInstance(){
        if(instance == null){
            instance = new DataRepository();
        }
        return instance;
    }

    public MutableLiveData<MovieApiResponse> getNowPlayingMovieList(int pageNumber){
        return mApiRepository.getNowPlayingMovieList(pageNumber);
    }

    public MutableLiveData<MovieApiResponse> getSimilarMovieList(int movieId, int pageNumber){
        return mApiRepository.getSimilarMovieList(movieId, pageNumber);
    }
}
