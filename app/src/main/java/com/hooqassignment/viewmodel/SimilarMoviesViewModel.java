package com.hooqassignment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.hooqassignment.repository.DataRepository;
import com.hooqassignment.repository.MovieApiResponse;


public class SimilarMoviesViewModel extends ViewModel {
    private MutableLiveData<MovieApiResponse> mutableLiveData;
    private DataRepository mDataRepository;

    public void init(){

        mDataRepository = DataRepository.getInstance();

    }

    public MutableLiveData<MovieApiResponse> getSimilarMovieList(int movieId,int pageNumber) {
        return mDataRepository.getSimilarMovieList(movieId,pageNumber);
    }


}