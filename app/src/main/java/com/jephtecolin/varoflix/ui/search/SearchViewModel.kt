package com.jephtecolin.varoflix.ui.search

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
class SearchViewModel @Inject constructor(
    val movieDataSource: MovieDataSource
): ViewModel() {
    val movies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())

    fun getMoviesByKeywords(keywords: String) {
        viewModelScope.launch {
            movieDataSource.getMoviesByKeywords(keywords).collectLatest {
                if (it != null) {
                    movies.value = it.results
                }
            }
        }

    }
}

