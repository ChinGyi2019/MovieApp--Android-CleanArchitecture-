package com.chingyi.moviedbappcleanarchitecture.feature.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asFlow
import androidx.recyclerview.widget.LinearLayoutManager
import com.chingyi.common.helper.Coroutine
import com.chingyi.domain.upcomming.model.Movie
import com.chingyi.moviedbappcleanarchitecture.R
import com.chingyi.moviedbappcleanarchitecture.databinding.FragmentHomeBinding
import com.chingyi.moviedbappcleanarchitecture.feature.home.adapter.BestPopularMoviesRecyclerAdapter
import com.chingyi.moviedbappcleanarchitecture.feature.home.adapter.GenreListAdapter
import com.chingyi.moviedbappcleanarchitecture.feature.home.adapter.SliderImageAdapter
import com.chingyi.moviedbappcleanarchitecture.feature.home.listing.GenreViewItem
import com.chingyi.moviedbappcleanarchitecture.helper.AppExecutors
import com.chingyi.moviedbappcleanarchitecture.helper.autoCleared
import com.chingyi.moviedbappcleanarchitecture.helper.view.HorizontalContentItemDecoration
import com.chingyi.moviedbappcleanarchitecture.helper.viewstate.ViewState
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel>()
    private var _binding : FragmentHomeBinding? = null
    private val adapter = SliderImageAdapter()

    private val binding get() = _binding !!

    @Inject
    lateinit var appExecutors : AppExecutors

    private var popularMovieAdapter by autoCleared<BestPopularMoviesRecyclerAdapter>()//by autoCleared<BestPopularMoviesRecyclerAdapter>()
    private var popularSeriesAdapter by autoCleared<BestPopularMoviesRecyclerAdapter>() //by autoCleared<BestPopularMoviesRecyclerAdapter>()
    private lateinit var genreListAdapter : GenreListAdapter //by autoCleared<GenreListAdapter>()
    private var genreMovieAdapter by autoCleared<BestPopularMoviesRecyclerAdapter>()




    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater : LayoutInflater ,
        container : ViewGroup? ,
        savedInstanceState : Bundle?
    ) : View? {


        _binding = FragmentHomeBinding.inflate(inflater , container , false)
        val root : View = binding.root
        initView()
        getUpComingMovie()
        getPopularMovies()
        getPopularSeries()
        bindGenreList()
        preSubmitGenreMovies()
        return root
    }

    @SuppressLint("LogNotTimber")
    private fun initNetworkCall() {
        homeViewModel.getUpComingMovieList()
        homeViewModel.getPopularMovieList()
        homeViewModel.getPopularSeriesList()
        homeViewModel.getGenreList()


        homeViewModel.addMoveSeriesByGenre()
        // Log.e("clicked" , moviesListByGenre.size.toString())

//        _moviesListByGenre.observe(viewLifecycleOwner) {
//            Log.e("clicked" , it.toString())
//            Log.e("clicked" , it.size.toString())
//        }

    }

//    @SuppressLint("LogNotTimber")
//    fun addMoveSeriesByGenre(){
//        Coroutine.main{
//
//        homeViewModel.movieAndSeriesList.observe(viewLifecycleOwner) { moveAndSeriesList->
//            Log.e("movieAndSeries", moveAndSeriesList.size.toString())
//            moveAndSeriesList.forEach { movieSeries->
//                movieSeries.genreIDS?.forEach { genreId->
//                    if(moviesListByGenre[genreId]?.isNotEmpty() == true){
//                        val setMovie = HashSet<Movie>()
//                       moviesListByGenre[genreId]?.let { setMovie.addAll(it.plus(movieSeries)) }
//                        //setMovie.add(movieSeries)
//                        moviesListByGenre[genreId] = setMovie
//                            _moviesListByGenre.value = (moviesListByGenre)
//
//
//                    }else{
//                        val setMovie = HashSet<Movie>()
//                        setMovie.add(movieSeries)
//                        moviesListByGenre[genreId] = setMovie
//                        _moviesListByGenre.value = (moviesListByGenre)
//                    }
//
//
//                }
//
//            }
//        }
//        }
//    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun initView() {
        binding.imageSlider.apply {
            setSliderAdapter(adapter)
            setIndicatorAnimation(IndicatorAnimationType.SCALE)
            setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
            indicatorSelectedColor = resources.getColor(R.color.color_yellow)
            indicatorUnselectedColor = Color.WHITE
            scrollTimeInSec = 4 //set scroll delay in seconds :
            startAutoCycle()
        }

        setUpBestPopularRecyclerAdapter()
        setUpGenreRecyclerAdapter()
        initNetworkCall()

        onTapGenreItemClickListener()

        upComingMovieClicked()


    }

    private fun setUpBestPopularRecyclerAdapter() {

        //PopualrSeries
        val _popularSeriesAdapter = BestPopularMoviesRecyclerAdapter(
            appExecutors = appExecutors ,
        )
        this.popularSeriesAdapter = _popularSeriesAdapter

        binding.recyclerPopularSeries.apply {
            layoutManager = LinearLayoutManager(
                requireContext() ,
                LinearLayoutManager.HORIZONTAL , false
            )
            adapter = popularSeriesAdapter
            setHasFixedSize(true)
            addItemDecoration(
                HorizontalContentItemDecoration(
                    startOffset = 64 ,
                    itemOffset = 12
                )
            )

        }
        //Popular Movie
        val _popularMovieAdapter = BestPopularMoviesRecyclerAdapter(
            appExecutors = appExecutors ,
        )
        this.popularMovieAdapter = _popularMovieAdapter

        binding.recyclerPopularMovie.apply {
            layoutManager = LinearLayoutManager(
                requireContext() ,
                LinearLayoutManager.HORIZONTAL , false
            )
            adapter = popularMovieAdapter
            setHasFixedSize(true)
            addItemDecoration(
                HorizontalContentItemDecoration(
                    startOffset = 64 ,
                    itemOffset = 12
                )
            )

        }


    }

    private fun setUpGenreRecyclerAdapter() {
        val _genreListAdapter = GenreListAdapter(
            homeViewModel ,
            appExecutors = appExecutors ,
        )
        this.genreListAdapter = _genreListAdapter

        binding.genreTitleRecycler.apply {
            layoutManager = LinearLayoutManager(
                requireContext() ,
                LinearLayoutManager.HORIZONTAL , false
            )
            adapter = genreListAdapter
            setHasFixedSize(true)
            addItemDecoration(
                HorizontalContentItemDecoration(
                    startOffset = 64 ,
                    itemOffset = 12
                )
            )

        }

        //Genre Movie
        val _genreMovieAdapter = BestPopularMoviesRecyclerAdapter(
            appExecutors = appExecutors ,
        )
        this.genreMovieAdapter = _genreMovieAdapter

        binding.genreMovieRecycler.apply {
            layoutManager = LinearLayoutManager(
                requireContext() ,
                LinearLayoutManager.HORIZONTAL , false
            )
            adapter = genreMovieAdapter
            setHasFixedSize(true)
            addItemDecoration(
                HorizontalContentItemDecoration(
                    startOffset = 64 ,
                    itemOffset = 12
                )
            )

        }
    }

    private fun getUpComingMovie() {
        homeViewModel.upComingMovieList.observe(viewLifecycleOwner) { viewState->
            when (viewState) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    adapter.movieList.addAll(viewState.value)
                    adapter.notifyDataSetChanged()
                }
                is ViewState.Error -> {
                    Timber.w(viewState.exception)
                }

            }
        }
    }

    private fun getPopularMovies() {
        homeViewModel.popularMovieList.observe(viewLifecycleOwner) { viewState->
            when (viewState) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    popularMovieAdapter.submitList(viewState.value)
                    //  homeViewModel.addMovieAndSeries(viewState.value)

                }
                is ViewState.Error -> {
                    Timber.w(viewState.errorMessage)
                }

            }
        }
    }

    private fun getPopularSeries() {
        homeViewModel.popularSeriesList.observe(viewLifecycleOwner) { viewState->
            when (viewState) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    popularSeriesAdapter.submitList(viewState.value)
                }
                is ViewState.Error -> {
                    Timber.w(viewState.errorMessage)
                }

            }
        }
    }


    private fun bindGenreList() {
        homeViewModel.genreViewItemList.observe(viewLifecycleOwner) { viewState->


            when (viewState) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {

                    genreListAdapter.submitList(viewState.value)


                }
                is ViewState.Error -> {
                    Timber.w(viewState.errorMessage)
                }

            }
        }
    }

    @SuppressLint("LogNotTimber")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun upComingMovieClicked() {
        popularMovieAdapter.movieItemClick = {
        }


    }

    @SuppressLint("LogNotTimber")
    private fun onTapGenreItemClickListener() {
        genreListAdapter.genreItemClick = { item->
            homeViewModel.selectedGenre.value = item.name ?: ""
            Log.e("clicked" , item.id.toString())
            binding.genreMovieRecycler.scrollToPosition(0)
            genreMovieAdapter.submitList(homeViewModel.moviesListByGenre[item.id]?.toList())


        }


    }



    private fun preSubmitGenreMovies() {
        homeViewModel.genreViewItemList.observe(viewLifecycleOwner) { viewState->
            when (viewState) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    //NOTE:- Pre insert Movies

                        homeViewModel._moviesListByGenre.observe(viewLifecycleOwner) { map->
                            genreMovieAdapter.submitList(
                                map[viewState.value.first().id]?.toList() ?: emptyList()
                            )
                        }

                }
                is ViewState.Error -> {
                    Timber.w(viewState.errorMessage)
                }

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}