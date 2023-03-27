package com.jephtecolin.varoflix.ui.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jephtecolin.varoflix.data.model.Movie
import com.jephtecolin.varoflix.data.model.MovieDetail
import com.jephtecolin.varoflix.data.remote.MovieDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDataSource: MovieDataSource,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val MOVIE_ID_KEY = "movieId"
    }

    private val _movieDetail = MutableStateFlow<MovieDetail?>(null)
    val movieDetail: StateFlow<MovieDetail?> = _movieDetail.asStateFlow()

    init {
        val movieId = savedStateHandle.get<Long>(MOVIE_ID_KEY)
            ?: throw IllegalArgumentException("Movie ID must be provided")

        viewModelScope.launch {
            //val movieDetail = movieDataSource.getMovieDetail(movieId)

            movieDataSource.getMovieDetail(movieId).collect { value ->
                _movieDetail.value = value
            }
        }
    }

//    fun loadMovieDetail(movieId: Long) {
//        savedStateHandle[MOVIE_ID_KEY] = movieId
//
//        viewModelScope.launch {
//            //val movieDetail = movieDataSource.getMovieDetail(movieId)
//
//            movieDataSource.getMovieDetail(movieId).collect { value ->
//                _movieDetail.value = value
//            }
//        }
//    }





//    var tvDetail: MutableState<Movie?> = mutableStateOf(null)
//
//    val tvId: MutableStateFlow<Long?> = MutableStateFlow(null)
//
//    private val detailFlow = tvId.flatMapLatest { tvId ->
//
//        if (tvId != null) {
//            movieDataSource.getMovieDetail(tvId)
//                .onCompletion { _tvLoadingState.value = NetworkState.SUCCESS }
//                .catch { e ->
//                    Timber.e(e)
//                }
//        } else {
//            emptyFlow()
//        }
//
//
//    }

}