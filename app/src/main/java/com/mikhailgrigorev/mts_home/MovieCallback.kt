package com.mikhailgrigorev.mts_home

import androidx.recyclerview.widget.DiffUtil
import com.mikhailgrigorev.mts_home.movieData.Movie

class MovieCallback (
    private val oldList: List<Movie>,
    private val newList: List<Movie>,
        ): DiffUtil.Callback(){
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}