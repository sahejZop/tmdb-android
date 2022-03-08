package com.example.tmdb.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.databinding.MoviecardBinding
import com.example.tmdb.data.MovieListData
import com.example.tmdb.data.MovieEntity

class DashboardRecyclerAdapter(private val listener: OnClick) : RecyclerView.Adapter<DashboardRecyclerAdapter.viewHolder>() {

    lateinit var binding : MoviecardBinding
    var moviesList: MovieListData? = null

    fun setMovieList(moviesListpar: MovieListData){
        moviesList = moviesListpar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.moviecard, parent, false)
        binding = MoviecardBinding.bind(view)

        val viewHolderobj = viewHolder(view, binding)

        view.setOnClickListener{
            listener.onItemClicked(moviesList!!.results[viewHolderobj.adapterPosition])
        }

        return viewHolderobj
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val BASE_URL = "https://image.tmdb.org/t/p/original"
        val dataObj = moviesList?.results?.get(position)

        holder.moviename.text = dataObj?.title
        holder.releaseDate.text = dataObj?.release_date
        holder.rating.rating = dataObj?.vote_average?.toFloat()?.div(2) ?: 0f

        Glide.with(holder.itemView.context).load(BASE_URL + dataObj?.poster_path).into(holder.binding.movieimg)
    }

    override fun getItemCount(): Int {
        return moviesList?.results?.size ?: 0
    }

    class viewHolder(view: View, val binding: MoviecardBinding): ViewHolder(view){
        var moviename = binding.movienameText
        var releaseDate = binding.releaseDate
        var rating = binding.rating
    }
}

interface OnClick{
    fun onItemClicked(par: MovieEntity)
}