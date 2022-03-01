package com.example.tmdb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.data.MovieData
import com.example.tmdb.databinding.FragmentMovieDescriptionBinding
import java.lang.System.load

class MovieDescriptionFragment(val MovieDataObj: MovieData?) : Fragment() {

    lateinit var binding: FragmentMovieDescriptionBinding
    val BASE_URL: String = "https://image.tmdb.org/t/p/original"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        return binding.root
    }
}