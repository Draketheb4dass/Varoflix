package com.jephtecolin.varoflix.data.model

import androidx.annotation.Keep
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

@Keep
data class MovieDetail(
    @NonNull
    var id: Long,
    var title: String,
    @SerializedName("original_title")
    var originalTitle: String,
    var overview: String?,
    //@ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("poster_path")
    //@ColumnInfo(name = "poster_path")
    var posterPath: String?,
    @SerializedName("vote_count")
    //@ColumnInfo(name = "vote_count")
    var voteCount: Int,
    //@ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var voteAverage: Float,
)

fun MovieDetail.toMovie() = Movie(
    id = id.toString(),
    title = title,
    overview = overview,
    backdropPath = backdropPath,
    posterPath = posterPath,
    voteCount = voteCount,
    voteAverage = voteAverage
)
