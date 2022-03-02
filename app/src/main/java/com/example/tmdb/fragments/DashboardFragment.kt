package com.example.tmdb.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdb.R
import com.example.tmdb.adapters.OnClick
import com.example.tmdb.adapters.dashboardrecycleradapter
import com.example.tmdb.apiServices.movieApiInterface
import com.example.tmdb.data.MovieData
import com.example.tmdb.databinding.DashboardBinding
import com.example.tmdb.viewmodels.dashboardViewModel

class DashboardFragment(
    private val viewModel: dashboardViewModel
) : Fragment(), OnClick {

    lateinit var binding: DashboardBinding
    private val retrofitService = movieApiInterface.getInstance()
    private val adapter = dashboardrecycleradapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DashboardBinding.inflate(layoutInflater)

        binding.chipPopular.setOnClickListener(View.OnClickListener {
            viewModel.changeCategory(POPULAR)
        })

        binding.chipToprated.setOnClickListener(View.OnClickListener {
            viewModel.changeCategory(TOP_RATED)
        })

        binding.chipUpcoming.setOnClickListener(View.OnClickListener {
            viewModel.changeCategory(UPCOMING)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclermovieslist.setHasFixedSize(true)
        binding.recyclermovieslist.setItemViewCacheSize(20)

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            binding.recyclermovieslist.layoutManager = GridLayoutManager(activity, 2)
            adapter.setMovieList(it)
            binding.recyclermovieslist.adapter = adapter
        })

        viewModel.getMovieListquery("popular")
    }

    override fun onItemClicked(par: MovieData?) {
        Log.d("dash", "${par?.title}")

        val movieDescriptionFragment = MovieDescriptionFragment(par, viewModel)
        viewModel.changeMovie(par!!.id.toString())

        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl, movieDescriptionFragment)
            addToBackStack("dashboard")
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
        when (binding.chipGroup.checkedChipId) {
            binding.chipPopular.id -> viewModel.changeCategory(POPULAR)
            binding.chipToprated.id -> viewModel.changeCategory(TOP_RATED)
            binding.chipUpcoming.id -> viewModel.changeCategory(UPCOMING)
        }
    }

    companion object {
        const val POPULAR = "popular"
        const val TOP_RATED = "top_rated"
        const val UPCOMING = "upcoming"
    }

}