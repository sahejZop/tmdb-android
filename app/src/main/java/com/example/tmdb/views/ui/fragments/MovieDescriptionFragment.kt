package com.example.tmdb.views.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentMovieDescriptionBinding
import com.example.tmdb.models.MovieEntity
import com.example.tmdb.views.viewmodels.DashboardViewModel

class MovieDescriptionFragment(
    private val MovieDataObj: MovieEntity,
    private val viewModel: DashboardViewModel
) : Fragment() {

    private lateinit var binding: FragmentMovieDescriptionBinding
    val baseUrl: String = "https://image.tmdb.org/t/p/original"

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieDescriptionBinding.inflate(layoutInflater)
        binding.movieTitle.text = MovieDataObj.title
        binding.movieOverview.text = MovieDataObj.overview
        binding.movieReleaseDate.text = MovieDataObj.release_date

        context?.let { Glide.with(it).load(baseUrl + MovieDataObj.poster_path).into(binding.moviePoster) }
        context?.let { Glide.with(it).load(baseUrl + MovieDataObj.backdrop_path).into(binding.movieBackdrop) }

        viewModel.isFav.observe(viewLifecycleOwner) {
            if (viewModel.isFav.value!!) {
                binding.favBtn.setImageResource(
                    R.drawable.ic_baseline_favorite_24
                )
            } else {
                binding.favBtn.setImageResource(
                    R.drawable.ic_baseline_favorite_25
                )
            }
        }

        binding.favBtn.setOnClickListener {
            viewModel.onFavButtonPress(MovieDataObj)
        }

        return binding.root
    }
}
