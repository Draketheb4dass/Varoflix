package com.jephtecolin.varoflix.data.remote

import com.jephtecolin.varoflix.data.remote.response.MoviesResponseModel
import retrofit2.Response
import retrofit2.http.GET


interface TMDBService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies() : Response<MoviesResponseModel>
}