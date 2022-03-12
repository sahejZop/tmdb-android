package com.example.tmdb.views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.databinding.DashboardBinding
import com.example.tmdb.models.MovieEntity
import com.example.tmdb.models.MovieListData
import com.example.tmdb.util.Constant.Companion.QUERY_PAGE_SIZE
import com.example.tmdb.views.adapters.DashboardRecyclerAdapter
import com.example.tmdb.views.adapters.OnClick
import com.example.tmdb.views.viewmodels.DashboardViewModel

class DashboardFragment(
    private val viewModel: DashboardViewModel
) : Fragment(), OnClick {

    private lateinit var binding: DashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieListQuery()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DashboardBinding.inflate(layoutInflater)

        binding.chipPopular.setOnClickListener {
            viewModel.changeCategory(POPULAR)
        }

        binding.chipTopRated.setOnClickListener {
            viewModel.changeCategory(TOP_RATED)
        }

        binding.chipUpcoming.setOnClickListener {
            viewModel.changeCategory(UPCOMING)
        }

        binding.chipFav.setOnClickListener {
            viewModel.currentChip = "favourite"
            viewModel.showFavourite()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerMoviesList.apply {
            layoutManager = GridLayoutManager(activity, 2)
            addOnScrollListener(this@DashboardFragment.scrollListener)
        }

        val adapter = DashboardRecyclerAdapter(this)
        binding.recyclerMoviesList.adapter = adapter

        viewModel.movieList.observe(viewLifecycleOwner) {
            adapter.setMovieList(it)
        }

        viewModel.favMovies.observe(viewLifecycleOwner) {
            if (viewModel.currentChip == FAVOURITE) {
                adapter.setMovieList(MovieListData(1, it as MutableList<MovieEntity>))
            }
        }
    }

    override fun onItemClicked(movieEntity: MovieEntity) {
        val movieDescriptionFragment = MovieDescriptionFragment(movieEntity, viewModel)
        viewModel.changeMovie(movieEntity.id.toString())

        viewModel.getTrailer(movieEntity.id.toString())
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl, movieDescriptionFragment)
            addToBackStack(null)
            commit()
        }
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val notLoadingOrLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = notLoadingOrLastPage && isAtLastItem && isNotAtBeginning &&
                isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {
                viewModel.getMovieListQuery()
                isScrolling = false
            }
        }
    }

    companion object {
        const val POPULAR = "popular"
        const val TOP_RATED = "top_rated"
        const val UPCOMING = "upcoming"
        const val FAVOURITE = "favourite"
    }
}
