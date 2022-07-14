package com.hooqassignment.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import com.hooqassignment.Constants
import com.hooqassignment.R
import com.hooqassignment.repository.model.MovieModel
import com.hooqassignment.ui.fragments.MoviesFragment
import com.hooqassignment.viewmodel.SimilarMoviesViewModel

import java.util.ArrayList

class MovieDetailActivty : AppCompatActivity(), NextPageLoadListener {
    private val mMovieList = ArrayList<MovieModel>()
    private var similarMoviesViewModel: SimilarMoviesViewModel? = null
    //    private MovieDetailFragment mMovieDetailFragment;
    private var mMoviesFragment: MoviesFragment? = null
    private var mMovieModel: MovieModel? = null
    private val mNestedScrollView: NestedScrollView? = null
    private var mCurrentPageNumber = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        supportActionBar?.title = getString(R.string.txt_movie_details)
        mMoviesFragment = supportFragmentManager.findFragmentById(R.id.fragment_movie_list) as MoviesFragment?
        mMoviesFragment!!.setNextPageLoadListener(this)
        mMovieModel = intent.getSerializableExtra("movie") as MovieModel
        mMoviesFragment!!.setMovie(mMovieModel!!)
        mMoviesFragment!!.setNestedScroll(false)
        mMoviesFragment!!.mNestedScrollView = mNestedScrollView

        similarMoviesViewModel = ViewModelProvider(this).get(SimilarMoviesViewModel::class.java)
        similarMoviesViewModel!!.init()
        getData()
    }

    override fun loadNextPage() {
        mMoviesFragment!!.setReadyToLoadNext(false, true)
        mCurrentPageNumber++
        getData()
    }

    private fun getData() {
        similarMoviesViewModel!!.getSimilarMovieList(mMovieModel!!.id, mCurrentPageNumber).observe(this, Observer { movieApiResponse ->
            mMoviesFragment!!.updateMovieList(movieApiResponse!!.movieList)

            if(movieApiResponse?.responseStatus != Constants.RESPONSE_STATUS.SUCCESS){
                mMoviesFragment!!.showErrorView(movieApiResponse!!.responseStatus)
                mCurrentPageNumber --
            }else if (mCurrentPageNumber == movieApiResponse.pageCount) {
                mMoviesFragment!!.updateMovieList(movieApiResponse!!.movieList)
                mMoviesFragment!!.setReadyToLoadNext(false, false)
            } else {
                mMoviesFragment!!.updateMovieList(movieApiResponse!!.movieList)
                mMoviesFragment!!.setReadyToLoadNext(true, true)
            }
        })
    }
}
