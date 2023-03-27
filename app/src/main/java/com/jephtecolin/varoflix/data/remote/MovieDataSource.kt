package com.jephtecolin.varoflix.data.remote

import com.jephtecolin.varoflix.data.model.MovieDetail
import com.jephtecolin.varoflix.data.remote.response.MoviesResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class MovieDataSource @Inject constructor(private val tmdbService: TMDBService) {
    fun getNowPlayingMovies(): Flow<MoviesResponseModel?> = flow {
        val result = tmdbService.getNowPlayingMovies()
        if(result.isSuccessful) {
            emit(result.body())
        } else {
            Timber.d("Error fetching now playing movies: %s", result.errorBody())
        }
    }

    fun getMovieDetail(movieId: Long): Flow<MovieDetail?> = flow {
        val result = tmdbService.getMovieDetail(movieId)
        if(result.isSuccessful) {
            emit(result.body())
        } else {
            Timber.d("Error fetching movie detail: %s", result.message())
        }
    }

    fun getMoviesByKeywords(keywords: String): Flow<MoviesResponseModel?> = flow {
        val result = tmdbService.getMoviesByKeywords(keywords)
        if(result.isSuccessful) {
            emit(result.body())
        } else {
            Timber.d("Error fetching movie search: %s", result.message())
        }
    }
}