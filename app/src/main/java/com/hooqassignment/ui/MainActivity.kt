package com.hooqassignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hooqassignment.Constants
import com.hooqassignment.R

import com.hooqassignment.repository.model.MovieModel
import com.hooqassignment.ui.fragments.MoviesFragment
import com.hooqassignment.viewmodel.MoviesViewModel

import java.util.ArrayList

class MainActivity : AppCompatActivity(), NextPageLoadListener {
    private var moviesViewModel: MoviesViewModel? = null
    private val mMovieList = ArrayList<MovieModel>()
    private var mCurrentPageNumber = 1
    private var mMoviesFragment: MoviesFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.txt_now_playing)
        mMoviesFragment = supportFragmentManager.findFragmentById(R.id.fragment_movie_list) as MoviesFragment?
        mMoviesFragment!!.setNextPageLoadListener(this)

        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        moviesViewModel!!.init()
        getData()

    }

    override fun loadNextPage() {
        mMoviesFragment!!.setReadyToLoadNext(false, true)
        mCurrentPageNumber++
        getData()
    }

    private fun getData() {
        moviesViewModel!!.getNowPlayingMovieList(mCurrentPageNumber).observe(this, Observer { apiResponse ->
            if(apiResponse?.responseStatus != Constants.RESPONSE_STATUS.SUCCESS){
                mMoviesFragment!!.showErrorView(apiResponse!!.responseStatus)
                mCurrentPageNumber --
            }else if (mCurrentPageNumber == apiResponse.pageCount) {
                mMoviesFragment!!.updateMovieList(apiResponse!!.movieList)
                mMoviesFragment!!.setReadyToLoadNext(false, false)
            } else {
                mMoviesFragment!!.updateMovieList(apiResponse!!.movieList)
                mMoviesFragment!!.setReadyToLoadNext(true, true)
            }
        })
    }
}
