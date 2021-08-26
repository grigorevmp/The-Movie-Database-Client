package com.mikhailgrigorev.mts_home.mvvm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikhailgrigorev.mts_home.MoviesFragment
import com.mikhailgrigorev.mts_home.movieData.Movie
import com.mikhailgrigorev.mts_home.movieData.MovieRepository
import com.mikhailgrigorev.mts_home.movieData.MoviesModel
import com.mikhailgrigorev.mts_home.userData.UserRepository
import kotlinx.coroutines.launch

typealias MoviesFragmentViewState = MoviesFragment.ViewState

class MoviesViewModel : ViewModel() {

    private val model = MoviesModel()

    val viewState: LiveData<MoviesFragmentViewState> get() = _viewState
    private val _viewState = MutableLiveData<MoviesFragmentViewState>()

    val dataList: LiveData<List<Movie>> get() = _dataList
    private val _dataList = MutableLiveData<List<Movie>>()

    val currentMovie: LiveData<Movie> get() = _currentMovie
    private val _currentMovie = MutableLiveData<Movie>()

    private val movieRepository = MovieRepository()

    fun loadMovies() {
            viewModelScope.launch {
                try {
                    val movies = movieRepository.getAllMovies()
                    _dataList.postValue(movies)
                    _viewState.postValue(MoviesFragmentViewState(isDownloaded = false))
                } catch (e: Exception) {
                    // handler error
                }
            }
        //model.loadMovies(object : MoviesModel.LoadMovieCallback {
            // override fun onLoad(movies: List<Movie>?) {
            //     _dataList.postValue(movies)
            //     _viewState.postValue(MoviesFragmentViewState(isDownloaded = false))
            // }
        //})
    }

    // fun loadMovie(id: Long) {
    //     model.loadMovie(object : MoviesModel.LoadMovieByIdCallback {
    //         override fun onLoad(movie: Movie?) {
    //             _currentMovie.postValue(movie)
    //             _viewState.postValue(MoviesFragmentViewState(isDownloaded = false))
    //         }
    //     }, id)
    // }

    fun add(userData: Movie) {
    }

    fun clear(context: Context) {
        _viewState.postValue(MoviesFragmentViewState(isDownloaded = true))
        model.clearMovies(object : MoviesModel.CompleteCallback {
            override fun onComplete() {
                loadMovies()
            }
        })
    }
}