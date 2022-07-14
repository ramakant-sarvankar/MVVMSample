package com.hooqassignment.ui.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.hooqassignment.R
import com.hooqassignment.Util
import com.hooqassignment.repository.model.MovieModel

class MovieDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var mImgPoster: ImageView? = null
    private var txtTitle: TextView? = null
    private var txtReleaseYear: TextView? = null
    private var txtDescription: TextView? = null

    init {
        initViews(itemView)
    }

    private fun initViews(view: View) {
        mImgPoster = view.findViewById(R.id.img_movie_poster)
        txtTitle = view.findViewById(R.id.txt_title)
        txtReleaseYear = view.findViewById(R.id.txt_release_year)
        txtDescription = view.findViewById(R.id.txt_description)
    }

    fun updateData(movie: MovieModel?) {
        if (movie != null) {
            Util.loadImage(mImgPoster!!, Util.getBackdropImagePath(movie.backdropPath))
            txtTitle!!.text = movie.title
            Util.setSpanString(txtReleaseYear!!.context.resources.getString(R.string.release_year), movie.releaseDate, txtReleaseYear!!)
            Util.setSpanString(txtReleaseYear!!.context.resources.getString(R.string.overview), movie.overview, txtDescription!!)
        }
    }
}
