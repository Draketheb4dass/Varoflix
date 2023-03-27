package com.jephtecolin.varoflix.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jephtecolin.varoflix.data.local.MovieDao
import com.jephtecolin.varoflix.data.model.Movie
import com.jephtecolin.varoflix.data.model.MovieDetail
import com.jephtecolin.varoflix.data.remote.MovieDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDataSource: MovieDataSource,
    private val movieDao: MovieDao,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val MOVIE_ID_KEY = "movieId"
    }

    private val _movieDetail = MutableStateFlow<MovieDetail?>(null)
    val movieDetail: StateFlow<MovieDetail?> = _movieDetail.asStateFlow()

    fun addToFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.insertFavorite(movie)
        }
    }

    init {
        val movieId = savedStateHandle.get<Long>(MOVIE_ID_KEY)
            ?: throw IllegalArgumentException("Movie ID must be provided")

        viewModelScope.launch {
            movieDataSource.getMovieDetail(movieId).collect { value ->
                _movieDetail.value = value
            }
        }
    }

}