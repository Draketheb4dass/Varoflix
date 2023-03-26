package com.jephtecolin.varoflix.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jephtecolin.varoflix.data.model.Movie

data class MoviesResponseModel(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_results")
    val totalResult: Int,
    @SerializedName("total_pages")
    val totalPage: Int
)
