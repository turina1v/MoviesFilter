package com.turina1v.moviesfilter.ui.poster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.turina1v.moviesfilter.R
import com.turina1v.moviesfilter.data.entity.Movie
import kotlinx.android.synthetic.main.item_poster.view.*

class PosterAdapter : RecyclerView.Adapter<PosterAdapter.PosterViewHolder>() {
    var movies: List<Movie> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class PosterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            itemView.apply {
                Glide.with(itemView).load(movie.poster).into(image_view_poster)
            }
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
