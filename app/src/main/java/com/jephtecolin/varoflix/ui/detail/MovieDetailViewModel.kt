package com.jephtecolin.varoflix.ui.detail

import androidx.lifecycle.ViewModel
import com.jephtecolin.varoflix.data.remote.MovieDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    movieDataSource: MovieDataSource
) : ViewModel() {
}