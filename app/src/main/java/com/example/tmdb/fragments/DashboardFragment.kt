package com.example.tmdb.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdb.R
import com.example.tmdb.adapters.OnClick
import com.example.tmdb.adapters.RecyclerItemClickListenr
import com.example.tmdb.adapters.dashboardrecycleradapter
import com.example.tmdb.apiServices.movieApiInterface
import com.example.tmdb.data.MovieData
import com.example.tmdb.databinding.DashboardBinding
import com.example.tmdb.repository.repository
import com.example.tmdb.viewmodels.MyViewModelFactory
import com.example.tmdb.viewmodels.dashboardViewModel

class DashboardFragment : Fragment(), OnClick {

    lateinit var binding: DashboardBinding
    lateinit var viewModel: dashboardViewModel
    private val retrofitService = movieApiInterface.getInstance()
    val adapter = dashboardrecycleradapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this, MyViewModelFactory(repository(retrofitService))).
                                          get(dashboardViewModel::class.java)

        binding = DashboardBinding.inflate(layoutInflater)

        binding.chipPopular.setOnClickListener(View.OnClickListener {
            viewModel.changeCategory("popular")
        })

        binding.chipToprated.setOnClickListener(View.OnClickListener {
            viewModel.changeCategory("top_rated")
        })

        binding.chipUpcoming.setOnClickListener(View.OnClickListener {
            viewModel.changeCategory("upcoming")
        })

        /*
        binding.recyclermovieslist.addOnItemTouchListener(RecyclerItemClickListenr(requireContext(), binding.recyclermovieslist, object : RecyclerItemClickListenr.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.fl, MovieDescriptionFragment())
                    commit()
                }
            }
            override fun onItemLongClick(view: View?, position: Int) {
                TODO("do nothing")
            }
        }))
         */


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

        //viewModel.getMovieListquery("popular")
        viewModel.getMovieListquery("popular")
        //viewModel.changeCategory("trending")
    }

    override fun onItemClicked(par: MovieData?) {
        Log.d("dash", "${par?.title}")
        val movieDescriptionFragment = MovieDescriptionFragment(par)

        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl, movieDescriptionFragment)
            addToBackStack("dashboard")
            commit()
        }
    }
}