package com.jephtecolin.varoflix.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Entity(tableName = "favorite_table", primaryKeys = ["id"])
data class Movie(
    @NonNull
    var id: String,
    var title: String,
    var overview: String?,
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    var posterPath: String?,
    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    var voteCount: Int,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var voteAverage: Float,
)
