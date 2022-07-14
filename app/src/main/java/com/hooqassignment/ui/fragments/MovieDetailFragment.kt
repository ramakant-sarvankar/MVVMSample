package com.hooqassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.hooqassignment.R
import com.hooqassignment.Util
import com.hooqassignment.repository.model.MovieModel

import java.util.ArrayList

class MovieDetailFragment : Fragment() {
    private var mMovie: MovieModel? = null
    private var mImgPoster: ImageView? = null
    private var txtTitle: TextView? = null
    private var txtReleaseYear: TextView? = null
    private var txtDescription: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie_detail, null)

        initViews(view)
        updateData()

        return view
    }

    private fun initViews(view: View) {
        mImgPoster = view.findViewById(R.id.img_movie_poster)
        txtTitle = view.findViewById(R.id.txt_title)
        txtReleaseYear = view.findViewById(R.id.txt_release_year)
        txtDescription = view.findViewById(R.id.txt_description)
    }

    fun setMovie(movie: MovieModel) {
        this.mMovie = movie
        updateData()
    }

    private fun updateData() {
        if (mMovie != null) {
            Util.loadImage(mImgPoster!!, mMovie!!.backdropPath)
            txtTitle!!.text = mMovie!!.title
            txtReleaseYear!!.text = mMovie!!.releaseDate
            txtDescription!!.text = mMovie!!.overview
        }
    }
}
