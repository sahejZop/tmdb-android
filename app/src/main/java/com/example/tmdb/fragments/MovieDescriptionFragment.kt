package com.example.tmdb.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.database.MovieEntity
import com.example.tmdb.databinding.FragmentMovieDescriptionBinding
import com.example.tmdb.viewmodels.dashboardViewModel

class MovieDescriptionFragment(
    private val MovieDataObj: MovieEntity,
    private val viewModel: dashboardViewModel
    ) : Fragment() {

    private lateinit var binding: FragmentMovieDescriptionBinding
    val BASE_URL: String = "https://image.tmdb.org/t/p/original"

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieDescriptionBinding.inflate(layoutInflater)
        binding.movieTitle.text = MovieDataObj.title
        binding.movieOverview.text = MovieDataObj.overview
        binding.movieReleaseDate.text = MovieDataObj.release_date

        context?.let { Glide.with(it).load(BASE_URL + MovieDataObj.poster_path).into(binding.moviePoster) }
        context?.let { Glide.with(it).load(BASE_URL + MovieDataObj.backdrop_path).into(binding.movieBackdrop) }

        viewModel.isFav.observe(viewLifecycleOwner) {
            if (viewModel.isFav.value!!) {
                binding.favbtn.text = "unfavourite"
            } else
                binding.favbtn.text = "favourite"
        }

        binding.favbtn.setOnClickListener{
            viewModel.onFavButtonPress(MovieDataObj)
        }

        binding.testbtn.setOnClickListener{
            val dash = DashboardFragment(viewModel)

            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl, dash)
                commit()
            }
        }

        return binding.root
    }
}