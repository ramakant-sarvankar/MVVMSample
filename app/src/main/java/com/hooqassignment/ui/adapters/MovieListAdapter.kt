package com.hooqassignment.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hooqassignment.R
import com.hooqassignment.Util
import com.hooqassignment.repository.model.MovieModel
import com.hooqassignment.ui.NextPageLoadListener

import java.util.ArrayList

class MovieListAdapter(private val mOnItemClickListener: View.OnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mMovieList: List<MovieModel> = ArrayList()
    private var mShowLoaderAtEnd = false
    private var isReadyToLoadNext = false
    private var mNextPageLoadListener: NextPageLoadListener? = null
    private var mGridLayoutManager: GridLayoutManager? = null
    private var isShowDetailsAtTop = false

    fun setGridLayoutManager(layoutManager: GridLayoutManager) {
        mGridLayoutManager = layoutManager
    }

    fun setMovieList(movieList: List<MovieModel>) {
        mMovieList = movieList
    }

    fun setNextPageLoadListener(nextPageLoadListener: NextPageLoadListener) {
        mNextPageLoadListener = nextPageLoadListener
    }

    fun setShowDetailsAtTop(isShowDetailsAtTop: Boolean) {
        this.isShowDetailsAtTop = isShowDetailsAtTop
    }

    fun setReadyToLoadNext(isReadyToLoadNext: Boolean, isShowLoaderAtEnd: Boolean) {
        this.isReadyToLoadNext = isReadyToLoadNext
        mShowLoaderAtEnd = isShowLoaderAtEnd
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, itemViewType: Int): RecyclerView.ViewHolder {
        var view: View? = null
        lateinit var viewHolder: RecyclerView.ViewHolder
        when (itemViewType) {
            TYPE_LOADER -> {
                view = LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_loader, viewGroup, false)
                viewHolder = LoadingViewHolder(view!!)
            }
            TYPE_MOVIE -> {
                view = LayoutInflater.from(viewGroup.context).inflate(R.layout.movie_row, viewGroup, false)
                viewHolder = MovieViewHolder(view!!)
            }
            TYPE_MOVIE_DETAILS -> {
                view = LayoutInflater.from(viewGroup.context).inflate(R.layout.fragment_movie_detail, viewGroup, false)
                viewHolder = MovieDetailViewHolder(view!!)
            }
        }
        return viewHolder
    }


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val itemViewType = getItemViewType(position)
        val view: View? = null
        if (itemViewType == TYPE_MOVIE) {
            (viewHolder as MovieViewHolder).setData(mOnItemClickListener, mMovieList[position])
            Util.loadImage(viewHolder.mImgMoviePoster, Util.getImagePath(mMovieList[position].posterPath))
        } else if (itemViewType == TYPE_LOADER) {

        } else if (itemViewType == TYPE_MOVIE_DETAILS) {
            (viewHolder as MovieDetailViewHolder).updateData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return if (mShowLoaderAtEnd)
            mMovieList.size + 1
        else
            mMovieList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 && isShowDetailsAtTop)
            TYPE_MOVIE_DETAILS
        else if (position < mMovieList.size)
            TYPE_MOVIE
        else
            TYPE_LOADER
    }



    companion object {
        val TYPE_MOVIE_DETAILS = 0
        val TYPE_MOVIE = 1
        val TYPE_LOADER = 2
    }
}
