package com.example.tmdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdb.adapters.dashboardrecycleradapter
import com.example.tmdb.apiServices.movieApiInterface
import com.example.tmdb.databinding.DashboardBinding
import com.example.tmdb.repository.repository
import com.example.tmdb.viewmodels.MyViewModelFactory
import com.example.tmdb.viewmodels.dashboardViewModel

class DashboardFragment : Fragment() {

    lateinit var binding: DashboardBinding
    lateinit var viewModel: dashboardViewModel
    private val retrofitService = movieApiInterface.getInstance()
    val adapter = dashboardrecycleradapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this, MyViewModelFactory(repository(retrofitService))).
                                          get(dashboardViewModel::class.java)

        binding = DashboardBinding.inflate(layoutInflater)

        binding.chipNowplaying.setOnClickListener(View.OnClickListener {
            viewModel.getMovieList("en", 1)
        })

        binding.chipToprated.setOnClickListener(View.OnClickListener {
            viewModel.getTopRatedList("en", 1)
        })

        binding.chipUpcoming.setOnClickListener(View.OnClickListener {
            viewModel.getUpcomingMovies("en", 1)
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

        //viewModel.getMovieListquery("en")
        viewModel.getMovieList("en", 1)
        //viewModel.changeCategory("trending")
    }
}