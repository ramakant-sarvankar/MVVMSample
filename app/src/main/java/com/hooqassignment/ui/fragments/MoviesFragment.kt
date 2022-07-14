package com.hooqassignment.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hooqassignment.Constants
import com.hooqassignment.R
import com.hooqassignment.repository.model.MovieModel
import com.hooqassignment.ui.MovieDetailActivty
import com.hooqassignment.ui.NextPageLoadListener
import com.hooqassignment.ui.adapters.MovieListAdapter
import com.hooqassignment.ui.adapters.RecyclerViewItemDecorator

import java.util.ArrayList

class MoviesFragment : Fragment(){
    private val mMovieList = ArrayList<MovieModel>()
    private var mMovieListAdapter: MovieListAdapter? = null
    private var mRvMovieList: RecyclerView? = null
    private var mNextPageLoadListener: NextPageLoadListener? = null
    private var mGridLayoutManager: GridLayoutManager? = null
    var mNestedScrollView: NestedScrollView? = null
    private var isReadyToLoadNext = true
    private var isShowDetailsAtTop = false
    private val mMovie: MovieModel? = null
    private var mtxtError : TextView? = null
    private var mViewError : View? = null
    private var mBtnRetry : Button? = null
    private var isRetryClick = false
    private var mProgressBar : ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movies, null)
        initViews(view)
        initAdapter()
        return view
    }

    fun updateMovieList(movieList: List<MovieModel>) {
        this.mMovieList.addAll(movieList)
        updateAdapter()
        mViewError?.visibility = View.GONE
    }

    fun setMovie(movie: MovieModel) {
        isShowDetailsAtTop = true
        mMovieListAdapter!!.setShowDetailsAtTop(isShowDetailsAtTop)
        mMovieList.add(0, movie)
    }

    fun setReadyToLoadNext(isReadyToLoadNext: Boolean, isShowLoaderAtEnd: Boolean) {
        this.isReadyToLoadNext = isReadyToLoadNext
        mMovieListAdapter!!.setReadyToLoadNext(isReadyToLoadNext, isShowLoaderAtEnd)
    }

    fun showErrorView(errorType : Constants.RESPONSE_STATUS) {
        mProgressBar?.visibility = View.GONE
        isRetryClick = false
        if(errorType == Constants.RESPONSE_STATUS.NO_INTERNET && mMovieList.size == 0){
            mViewError?.visibility = View.VISIBLE
            mtxtError?.setText(R.string.no_internet_connection)
            mViewError?.visibility = View.VISIBLE
            mBtnRetry?.visibility = View.VISIBLE
        }else if(errorType == Constants.RESPONSE_STATUS.SERVER_FAILURE && mMovieList.size == 0){
            mViewError?.visibility = View.VISIBLE
            mtxtError?.setText(R.string.server_error)
            mViewError?.visibility = View.VISIBLE
            mBtnRetry?.visibility = View.VISIBLE
            isReadyToLoadNext = true
        }else if(errorType == Constants.RESPONSE_STATUS.NO_INTERNET && mMovieList.size > 0){
            mViewError?.visibility = View.GONE
            Toast.makeText(activity,R.string.no_internet_connection,Toast.LENGTH_LONG).show()
            mMovieListAdapter!!.setReadyToLoadNext(true, true)
        }else if(errorType == Constants.RESPONSE_STATUS.SERVER_FAILURE && mMovieList.size > 0){
            mViewError?.visibility = View.GONE
            Toast.makeText(activity,R.string.server_error,Toast.LENGTH_LONG).show()
            mMovieListAdapter!!.setReadyToLoadNext(true, true)
        }
    }

    private fun initViews(view: View) {
        mRvMovieList = view.findViewById(R.id.rv_movie_list)
        mtxtError = view.findViewById(R.id.txt_error_message)
        mViewError = view.findViewById(R.id.layout_error);
        mBtnRetry = view.findViewById(R.id.btn_retry)
        mProgressBar =view.findViewById(R.id.progressBar)
        mBtnRetry?.setOnClickListener(clickListener)

    }

    fun setNestedScroll(isNestedScroll: Boolean) {
        mRvMovieList!!.isNestedScrollingEnabled = isNestedScroll
    }

    fun setNextPageLoadListener(nextPageLoadListener: NextPageLoadListener) {
        mNextPageLoadListener = nextPageLoadListener
        if (mMovieListAdapter != null) {
            mMovieListAdapter!!.setNextPageLoadListener(nextPageLoadListener)
        }
    }

    private fun initAdapter() {
        mGridLayoutManager = GridLayoutManager(activity, 3)
        mGridLayoutManager!!.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (mMovieListAdapter!!.getItemViewType(position)) {
                    MovieListAdapter.TYPE_MOVIE -> return 1
                    MovieListAdapter.TYPE_LOADER -> return 3 //number of columns of the grid
                    MovieListAdapter.TYPE_MOVIE_DETAILS -> return 3
                    else -> return -1
                }
            }
        }
        mRvMovieList!!.addItemDecoration(RecyclerViewItemDecorator(activity!!, R.dimen.grid_gap))
        mRvMovieList!!.layoutManager = mGridLayoutManager
        mMovieListAdapter = MovieListAdapter(clickListener)
        mMovieListAdapter!!.setMovieList(mMovieList)
        mMovieListAdapter!!.setGridLayoutManager(mGridLayoutManager!!)
        mRvMovieList!!.adapter = mMovieListAdapter


    }

    private fun updateAdapter() {
        mNestedScrollView = null
        mMovieListAdapter!!.setMovieList(mMovieList)
        mMovieListAdapter!!.notifyDataSetChanged()

            mRvMovieList!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val totalItemCount = mGridLayoutManager!!.itemCount - 1
                    val firstVisibleItemPosition = mGridLayoutManager!!.findFirstVisibleItemPosition()
                    val lastVisibleItemPosition = mGridLayoutManager!!.findLastVisibleItemPosition()
                    if (lastVisibleItemPosition == totalItemCount && firstVisibleItemPosition > 0 && isReadyToLoadNext) {
                        isReadyToLoadNext = false
                        mNextPageLoadListener!!.loadNextPage()
                    }
                }
            })
    }


    val clickListener = View.OnClickListener {view ->

        when (view.getId()) {
            R.id.img_movie_poster -> goToMovieDetails(view)
            R.id.btn_retry ->
                if(!isRetryClick)
                {
                    retry()
                }
        }
    }

    private fun goToMovieDetails(v: View){
        val movie = v.getTag(R.id.movie_object) as MovieModel
        if (movie != null) {
            val intent = Intent(activity, MovieDetailActivty::class.java)
            intent.putExtra("movie", movie)
            startActivity(intent)
        }
    }

    private fun retry(){
        isRetryClick = true
        mProgressBar?.visibility = View.VISIBLE
        mViewError?.visibility = View.GONE
        mBtnRetry?.visibility = View.GONE
        mNextPageLoadListener!!.loadNextPage()
    }

}