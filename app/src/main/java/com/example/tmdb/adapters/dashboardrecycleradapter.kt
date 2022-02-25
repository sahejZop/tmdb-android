package com.example.tmdb.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.data.MovieListData
import com.example.tmdb.databinding.MoviecardBinding

class dashboardrecycleradapter() : RecyclerView.Adapter<dashboardrecycleradapter.viewHolder>() {

    lateinit var binding : MoviecardBinding
    var moviesList: MovieListData? = null

    fun setMovieList(moviesList: MovieListData){
        this.moviesList = moviesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dashboardrecycleradapter.viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.moviecard, parent, false)
        binding = MoviecardBinding.bind(view)
        return viewHolder(view, binding)
    }

    override fun onBindViewHolder(holder: dashboardrecycleradapter.viewHolder, position: Int) {
        val BASE_URL: String = "https://image.tmdb.org/t/p/original"
        val dataObj = moviesList?.results?.get(position)
        if (dataObj != null) {
            holder.moviename.text = dataObj.title
        }
        holder.rating.text = dataObj?.vote_average
        holder.releaseDate.text = dataObj?.release_date

        Glide.with(holder.itemView.context).load(BASE_URL + dataObj?.poster_path).into(holder.binding.movieimg)
    }

    override fun getItemCount(): Int {
        if (moviesList == null)
        {
            Log.d("adapter", "null h")
            return 0
        }
        return moviesList!!.results.size
    }

    inner class viewHolder(view: View, val binding: MoviecardBinding): RecyclerView.ViewHolder(view){
        var moviename = binding.movienameText
        var movieimg = binding.movieimg
        var releaseDate = binding.releaseDate
        var rating = binding.rating
    }

}