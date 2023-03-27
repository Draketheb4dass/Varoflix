package com.jephtecolin.varoflix.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jephtecolin.varoflix.data.local.MovieDao
import com.jephtecolin.varoflix.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieDao: MovieDao
) : ViewModel() {
    val favoriteMovies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            movieDao.getFavoriteMovies().collectLatest {
                favoriteMovies.value = it
            }
        }
    }
}