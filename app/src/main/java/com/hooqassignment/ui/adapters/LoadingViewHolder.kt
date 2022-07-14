package com.hooqassignment.ui.adapters

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView

import com.hooqassignment.R

class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    init {

        val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
    }
}
