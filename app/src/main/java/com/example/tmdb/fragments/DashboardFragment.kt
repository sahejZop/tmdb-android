package com.example.tmdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdb.R
import com.example.tmdb.adapters.OnClick
import com.example.tmdb.adapters.dashboardrecycleradapter
import com.example.tmdb.data.MovieListData
import com.example.tmdb.database.MovieEntity
import com.example.tmdb.databinding.DashboardBinding
import com.example.tmdb.viewmodels.dashboardViewModel

class DashboardFragment(
    private val viewModel: dashboardViewModel
) : Fragment(), OnClick {

    private lateinit var binding: DashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieListquery(POPULAR)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DashboardBinding.inflate(layoutInflater)

        binding.chipPopular.setOnClickListener{
            viewModel.changeCategory(POPULAR)
            viewModel.onFav = false
        }

        binding.chipToprated.setOnClickListener{
            viewModel.changeCategory(TOP_RATED)
            viewModel.onFav = false
        }

        binding.chipUpcoming.setOnClickListener {
            viewModel.changeCategory(UPCOMING)
            viewModel.onFav = false
        }

        binding.chipFav.setOnClickListener{
            viewModel.showFavourite()
            viewModel.onFav = true
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclermovieslist.setHasFixedSize(true)
        binding.recyclermovieslist.setItemViewCacheSize(20)
        binding.recyclermovieslist.layoutManager = GridLayoutManager(activity, 2)

        val adapter = dashboardrecycleradapter(this)

        viewModel.movieList.observe(viewLifecycleOwner) {
            adapter.setMovieList(it)
            binding.recyclermovieslist.adapter = adapter
        }

        viewModel.favMovies.observe(viewLifecycleOwner){
            if(viewModel.onFav)
            {
                adapter.setMovieList(MovieListData(1, it, it.size, 1))
                binding.recyclermovieslist.adapter = adapter
            }
        }
    }

    override fun onItemClicked(par: MovieEntity) {

        val movieDescriptionFragment = MovieDescriptionFragment(par, viewModel)
        viewModel.changeMovie(par.id.toString())

        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl, movieDescriptionFragment)
            addToBackStack(null)
            commit()
        }
    }

    /*
    override fun onResume() {
        super.onResume()
        when (binding.chipGroup.checkedChipId) {
            binding.chipPopular.id -> viewModel.changeCategory(POPULAR)
            binding.chipToprated.id -> viewModel.changeCategory(TOP_RATED)
            binding.chipFav.id -> viewModel.showFavourite()
            binding.chipUpcoming.id -> viewModel.changeCategory(UPCOMING)
        }
    }
     */

    companion object {
        const val POPULAR = "popular"
        const val TOP_RATED = "top_rated"
        const val UPCOMING = "upcoming"
    }
}