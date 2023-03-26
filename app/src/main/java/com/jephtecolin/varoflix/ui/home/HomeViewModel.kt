package com.jephtecolin.varoflix.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jephtecolin.varoflix.data.model.Movie
import com.jephtecolin.varoflix.data.remote.MovieDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieDataSource: MovieDataSource
) : ViewModel(){
    val movies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            movieDataSource.getNowPlayingMovies().collectLatest {
                if (it != null) {
                    movies.value = it.results
                }
            }
        }
    }
}