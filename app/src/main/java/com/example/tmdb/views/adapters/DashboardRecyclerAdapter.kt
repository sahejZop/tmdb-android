package com.example.tmdb.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.databinding.MovieCardBinding
import com.example.tmdb.models.MovieEntity
import com.example.tmdb.models.MovieListData

class DashboardRecyclerAdapter(private val listener: OnClick) : RecyclerView.Adapter<DashboardRecyclerAdapter.DashViewHolder>() {

    private lateinit var binding: MovieCardBinding
    private var moviesList: MovieListData? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieList(moviesListPar: MovieListData) {
        moviesList = moviesListPar
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        binding = MovieCardBinding.bind(view)

        val viewHolderObject = DashViewHolder(view, binding)

        view.setOnClickListener {
            listener.onItemClicked(moviesList!!.results[viewHolderObject.adapterPosition])
        }

        return viewHolderObject
    }

    override fun onBindViewHolder(holder: DashViewHolder, position: Int) {
        val baseUrl = "https://image.tmdb.org/t/p/original"
        val dataObj = moviesList?.results?.get(position)

        holder.movieName.text = dataObj?.title
        holder.releaseDate.text = dataObj?.release_date
        holder.rating.rating = dataObj?.vote_average?.toFloat()?.div(2) ?: 0f

        Glide.with(holder.itemView.context).load(baseUrl + dataObj?.poster_path).into(holder.binding.movieImg)
    }

    override fun getItemCount(): Int {
        return moviesList?.results?.size ?: 0
    }

    class DashViewHolder(view: View, val binding: MovieCardBinding) : ViewHolder(view) {
        var movieName = binding.movieNameText
        var releaseDate = binding.releaseDate
        var rating = binding.rating
    }
}

interface OnClick {
    fun onItemClicked(movieEntity: MovieEntity)
}
