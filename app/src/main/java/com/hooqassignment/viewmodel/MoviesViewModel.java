package com.hooqassignment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hooqassignment.repository.DataRepository;
import com.hooqassignment.repository.MovieApiResponse;
import com.hooqassignment.repository.model.MovieModel;

import java.util.List;

public class MoviesViewModel extends ViewModel {
    private MutableLiveData<MovieApiResponse> mutableLiveData;
    private List<MovieModel> mNowPlayingMoviesList;
    private DataRepository mDataRepository;

    public void init(){
//        if (mutableLiveData != null){
//            return;
//        }
        mDataRepository = DataRepository.getInstance();
//        mutableLiveData = mDataRepository.getNowPlayingMovieList(1);

    }

    public MutableLiveData<MovieApiResponse> getNowPlayingMovieList(int pageNumber) {
        return mDataRepository.getNowPlayingMovieList(pageNumber);
    }

    public MutableLiveData<MovieApiResponse> getSimilarMovieList(int movieId,int pageNumber) {
        return mDataRepository.getSimilarMovieList(movieId,pageNumber);
    }


}
