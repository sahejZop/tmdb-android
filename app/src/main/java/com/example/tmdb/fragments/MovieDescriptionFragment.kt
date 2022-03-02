package com.example.tmdb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.tmdb.data.MovieData
import com.example.tmdb.database.MovieEntity
import com.example.tmdb.databinding.FragmentMovieDescriptionBinding
import com.example.tmdb.viewmodels.dashboardViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieDescriptionFragment(
    val MovieDataObj: MovieData?,
    val viewModel: dashboardViewModel
    ) : Fragment() {

    lateinit var binding: FragmentMovieDescriptionBinding
    val BASE_URL: String = "https://image.tmdb.org/t/p/original"
    val movieclass = MovieEntity(MovieDataObj!!.id, MovieDataObj.title, MovieDataObj.poster_path)
    var isFav: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieDescriptionBinding.inflate(layoutInflater)
        binding.movieTitle.text = MovieDataObj?.title
        binding.movieOverview.text = MovieDataObj?.overview
        binding.movieReleaseDate.text = MovieDataObj?.release_date
        context?.let { Glide.with(it).load(BASE_URL + MovieDataObj?.poster_path).into(binding.moviePoster) }
        context?.let { Glide.with(it).load(BASE_URL + MovieDataObj?.backdrop_path).into(binding.movieBackdrop) }

        viewModel.currentMovie.observe(viewLifecycleOwner, Observer {

        })

        GlobalScope.launch {
            if (viewModel.isMovieInTable(MovieDataObj!!.id.toString())){
                binding.favbtn.text = "unfavourite"
                isFav = true
            }
            else{
                binding.favbtn.text = "favourite"
                isFav = false
            }
        }

        binding.favbtn.setOnClickListener{
            onButtonPress(movieclass, viewModel)
            /*
            GlobalScope.launch {
                if (!isFav){
                    viewModel.repository.moviedDatabaseHelperImpl.insertMovie(movieclass)
                    binding.favbtn.text = "unfavourite"
                    isFav = true
                }
                else{
                    viewModel.repository.moviedDatabaseHelperImpl.deleteMovie(movieclass)
                    binding.favbtn.text = "favourite"
                    isFav = false
                }
            }
             */
        }

        return binding.root
    }

    private fun onButtonPress(movieclass: MovieEntity, viewModel: dashboardViewModel){
        GlobalScope.launch {
            if (!isFav){
                viewModel.repository.moviedDatabaseHelperImpl.insertMovie(movieclass)
                isFav = true
            }
            else{
                viewModel.repository.moviedDatabaseHelperImpl.deleteMovie(movieclass)
                isFav = false
            }
        }
        if (!isFav)
            binding.favbtn.text = "unfavourite"
        else
            binding.favbtn.text = "favourite"
    }

}