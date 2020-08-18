package com.turina1v.moviesfilter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.turina1v.moviesfilter.R
import kotlinx.android.synthetic.main.item_poster.view.*

class PosterAdapter : RecyclerView.Adapter<PosterAdapter.PosterViewHolder>() {
    var movies: List<Int> = listOf(R.drawable.poster_example, R.drawable.poster_example, R.drawable.poster_example, R.drawable.poster_example, R.drawable.poster_example, R.drawable.poster_example, R.drawable.poster_example, R.drawable.poster_example, R.drawable.poster_example, R.drawable.poster_example, R.drawable.poster_example, R.drawable.poster_example)
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class PosterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(resId: Int) = with(itemView) {
            item_poster.setImageResource(resId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder =
        PosterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_poster,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) =
        holder.bind(movies[position])

    override fun getItemCount(): Int = movies.size
}
