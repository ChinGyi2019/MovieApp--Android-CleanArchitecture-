package com.chingyi.moviedbappcleanarchitecture.feature.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*
import com.chingyi.common.helper.Coroutine
import com.chingyi.domain.exception.NetworkException
import com.chingyi.domain.genre.model.MovieGenre
import com.chingyi.domain.genre.usecase.GetGenreList
import com.chingyi.domain.popular.movie.usecase.GetPopularMovies
import com.chingyi.domain.popular.series.GetPopularSeries
import com.chingyi.domain.upcomming.model.Movie
import com.chingyi.domain.upcomming.usecase.GetUpComingMovieList
import com.chingyi.moviedbappcleanarchitecture.feature.home.listing.GenreViewItem
import com.chingyi.moviedbappcleanarchitecture.feature.home.listing.toGenreViewItem
import com.chingyi.moviedbappcleanarchitecture.helper.viewstate.ViewStateLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val upComingMovieUseCase : GetUpComingMovieList,
    private val popularMovieUseCase : GetPopularMovies,
    private val popularSeriesUseCase : GetPopularSeries,
    private val genreListUseCase : GetGenreList
) : ViewModel() {

    var selectedGenre = MutableLiveData<String>()

    //MARK:- UpComing
    private var _upComingMovieList  = ViewStateLiveData<List<Movie>>()
    val upComingMovieList : ViewStateLiveData<List<Movie>>  get() =  _upComingMovieList

    private var _popularMovieList  = ViewStateLiveData<List<Movie>>()
    val popularMovieList : ViewStateLiveData<List<Movie>>  get() =  _popularMovieList

    private var _popularSeriesList  = ViewStateLiveData<List<Movie>>()
    val popularSeriesList : ViewStateLiveData<List<Movie>>  get() =  _popularSeriesList

    private var _genreViewItemList  = ViewStateLiveData<List<GenreViewItem>>()

    val genreViewItemList : ViewStateLiveData<List<GenreViewItem>> get() = _genreViewItemList




    private var movieAndSeriesList =  MutableLiveData<List<Movie>>()
    private var _movieAndSeriesList  = mutableListOf<Movie>()

    private var _genreList = ArrayList<GenreViewItem>()

    val _moviesListByGenre = MutableLiveData<HashMap<Long , Set<Movie>>>()
    var moviesListByGenre = HashMap<Long , Set<Movie>>()

    @SuppressLint("LogNotTimber")
    fun  removeEmptyGenre(list : List<GenreViewItem>) : ArrayList<GenreViewItem>{

            _genreList.addAll(list)
            _genreList.removeAll { item->
             // val result = moviesListByGenre.filter { it.key == item.id }
              return@removeAll moviesListByGenre.containsKey(item.id)
        }
        Log.e("removed Gerne List",_genreList.size.toString())
       return  _genreList
    }

    @SuppressLint("LogNotTimber")
    fun addMoveSeriesByGenre(){
        Coroutine.main{

            movieAndSeriesList.asFlow().collect { moveAndSeriesList->
                moveAndSeriesList.forEach { movieSeries->
                    movieSeries.genreIDS?.forEach { genreId->
                        if(moviesListByGenre[genreId]?.isNotEmpty() == true){
                            val setMovie = HashSet<Movie>()
                            moviesListByGenre[genreId]?.let { setMovie.addAll(it.plus(movieSeries)) }
                            moviesListByGenre[genreId] = setMovie
                            _moviesListByGenre.postValue(moviesListByGenre)


                        }else{
                            val setMovie = HashSet<Movie>()
                            setMovie.add(movieSeries)
                            moviesListByGenre[genreId] = setMovie
                            _moviesListByGenre.postValue(moviesListByGenre)
                        }


                    }

                }
            }
        }
    }
    private fun addMovieAndSeries(list : List<Movie>){

            _movieAndSeriesList.addAll(_movieAndSeriesList.plus(list))
            movieAndSeriesList.postValue(_movieAndSeriesList)



    }

    fun getUpComingMovieList(){
        _upComingMovieList.postLoading()
        Coroutine.io{

        try {
           val data =  upComingMovieUseCase.execute(GetUpComingMovieList.Params(param = ""))
            _upComingMovieList.postSuccess(data = data)
            addMovieAndSeries(data)
        }catch (e : Exception){
            if (e is NetworkException){
                _upComingMovieList.postError(e,e.message.toString())
            }
        }

//         val movieNames =    upComingMovieUseCase.execute(GetUpComingMovieList.Params(param = ""))
//                .map { it.title }.reduce { acc, name ->
//                "${acc+name}, " }
//            _text.postValue(movieNames ?: "")
        }


    }

     fun getPopularMovieList(){
        _popularMovieList.postLoading()
        Coroutine.io{

            try {
                val data =  popularMovieUseCase.execute(GetPopularMovies.Params(page = 1))
                _popularMovieList.postSuccess(data = data)
                addMovieAndSeries(data)

            }catch (e : Exception){
                if (e is NetworkException){
                    _popularMovieList.postError(e,e.message.toString())
                }
            }

        }


    }

     fun getPopularSeriesList(){
        _popularSeriesList.postLoading()
        Coroutine.io{

            try {
                val data =  popularSeriesUseCase.execute(GetPopularSeries.Params(page = 1))
                _popularSeriesList.postSuccess(data = data)
                addMovieAndSeries(data)

            }catch (e : Exception){
                if (e is NetworkException){
                    _popularSeriesList.postError(e,e.message.toString())
                }
            }

        }


    }

    fun getGenreList(){
        _genreViewItemList.postLoading()
        Coroutine.io{

            try {
                val data =  genreListUseCase.execute(GetGenreList.Params(value = 0))
                //Prefix first selected
               selectedGenre.postValue(data.first().name ?: "")
                Log.e("Unremoved un List",data.size.toString())
                _genreViewItemList.postSuccess(data = removeEmptyGenre(list =
                data.map { it.toGenreViewItem() }))
//                _genreViewItemList.postSuccess(data = data.map {
//                    it.toGenreViewItem()
//                })
            }catch (e : Exception){
                if (e is NetworkException){
                    _genreViewItemList.postError(e,e.message.toString())
                }
            }

        }


    }
}