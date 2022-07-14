package com.hooqassignment.ui.adapters

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

import com.hooqassignment.R
import com.hooqassignment.repository.model.MovieModel

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var mImgMoviePoster: ImageView

    init {
        initViews(itemView)
    }

    fun setData(onClickListener: View.OnClickListener, movieModel: MovieModel) {
        mImgMoviePoster.setTag(R.id.movie_object, movieModel)
        mImgMoviePoster.setOnClickListener(onClickListener)
    }

    private fun initViews(itemView: View) {
        mImgMoviePoster = itemView.findViewById(R.id.img_movie_poster)
    }
}
