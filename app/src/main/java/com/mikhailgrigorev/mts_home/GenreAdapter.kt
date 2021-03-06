package com.mikhailgrigorev.mts_home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikhailgrigorev.mts_home.genreData.GenreData

interface OnGenreItemClickListener {
    fun onGenreItemClick(genre: String)
}

class GenreAdapter(
    context: Context,
    var genres: List<GenreData>,
    private val itemClickListener: OnGenreItemClickListener):
    RecyclerView.Adapter<GenreViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(inflater.inflate(R.layout.item_tag, parent, false))
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        getGenreAt(position)?.let { holder.bind(it, itemClickListener) }
    }

    override fun getItemCount() = genres.size

    private fun getGenreAt(position: Int): GenreData? {
        return when {
            genres.isEmpty() -> null
            position >= genres.size -> null
            else -> genres[position]
        }
    }
}