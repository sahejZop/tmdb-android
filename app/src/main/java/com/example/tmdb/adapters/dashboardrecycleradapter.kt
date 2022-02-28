package com.example.tmdb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.data.MovieData
import com.example.tmdb.data.MovieListData
import com.example.tmdb.databinding.MoviecardBinding

class dashboardrecycleradapter(private val listener: OnClick) : RecyclerView.Adapter<dashboardrecycleradapter.viewHolder>() {

    lateinit var binding : MoviecardBinding
    var moviesList: MovieListData? = null

    fun setMovieList(moviesListpar: MovieListData){
        moviesList = moviesListpar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dashboardrecycleradapter.viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.moviecard, parent, false)
        binding = MoviecardBinding.bind(view)
        val viewHolderobj = viewHolder(view, binding)

        view.setOnClickListener{
            /*
            moviesList?.results?.get(viewHolderobj.adapterPosition)
                ?.let { it1 -> listener.onItemClicked(it1) }

             */
            listener.onItemClicked(moviesList!!.results[viewHolderobj.adapterPosition])
        }

        return viewHolderobj
    }

    override fun onBindViewHolder(holder: dashboardrecycleradapter.viewHolder, position: Int) {
        val BASE_URL: String = "https://image.tmdb.org/t/p/original"
        val dataObj = moviesList?.results?.get(position)
        holder.moviename.text = dataObj?.title
        holder.rating.text = dataObj?.vote_average
        holder.releaseDate.text = dataObj?.release_date

        Glide.with(holder.itemView.context).load(BASE_URL + dataObj?.poster_path).into(holder.binding.movieimg)
    }

    override fun getItemCount(): Int {
        return moviesList?.results?.size ?: 0
    }

    class viewHolder(view: View, val binding: MoviecardBinding): ViewHolder(view){
        var moviename = binding.movienameText
        var movieimg = binding.movieimg
        var releaseDate = binding.releaseDate
        var rating = binding.rating

    }
}

interface OnClick{
    fun onItemClicked(par: MovieData?)
}