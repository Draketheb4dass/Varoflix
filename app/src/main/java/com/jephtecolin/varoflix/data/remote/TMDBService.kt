package com.jephtecolin.varoflix.data.remote

import com.jephtecolin.varoflix.data.model.MovieDetail
import com.jephtecolin.varoflix.data.remote.response.MoviesResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TMDBService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies() : Response<MoviesResponseModel>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Long) : Response<MovieDetail>

    @GET("search/movie")
    suspend fun getMoviesByKeywords(@Query("query") keywords: String) : Response<MoviesResponseModel>
}