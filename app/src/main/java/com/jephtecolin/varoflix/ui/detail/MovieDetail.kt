package com.jephtecolin.varoflix.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jephtecolin.varoflix.data.remote.Api
import com.jephtecolin.varoflix.R
import com.jephtecolin.varoflix.data.model.MovieDetail
import com.jephtecolin.varoflix.data.model.toMovie
import com.jephtecolin.varoflix.ui.components.PulsateEffect
import com.jephtecolin.varoflix.ui.components.PulsatingHeartIcon
import com.jephtecolin.varoflix.ui.components.bounceClick

@Composable
fun MovieDetail(
    viewModel: MovieDetailViewModel,
    onBackPressed: () -> Unit,
) {

    val movieDetail by viewModel.movieDetail.collectAsState()


    if (movieDetail != null) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(enabled = true, state = ScrollState(0))) {
            movieDetail?.voteAverage?.let {
                TVShowPoster(imageUrl = Api.getPosterPath(movieDetail?.backdropPath),
                    imageHeight = 300.dp, rating = it, originalTitle = movieDetail?.originalTitle?: "",
                title = movieDetail?.title?:"", onBackPressed = onBackPressed
                )
            }
            TVShowSummary(summary = movieDetail?.overview?: "")
            IconButton(onClick = { viewModel.addToFavorite(movieDetail!!.toMovie()) }, modifier = Modifier.bounceClick()) {
                Icon(Icons.Filled.Favorite, contentDescription = null, tint = Color.Red)
            }
        }
    }


}

// TODO This func has too many param
@Composable
private fun TVShowPoster(
    imageUrl: String,
    imageHeight: Dp,
    modifier: Modifier = Modifier,
    rating: Float,
    title: String,
    originalTitle: String,
    onBackPressed: () -> Unit
) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)) {


        Box(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp), contentAlignment = Alignment.BottomStart) {
            AsyncImage(
                model = imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .height(imageHeight),
                placeholder = ColorPainter(Color.Gray),
                error = ColorPainter(Color.DarkGray)
            )
            Column(modifier = Modifier.padding(start = 24.dp)) {
                Text(originalTitle, fontSize = 12.sp, color = Color.White)
                Text(title, fontSize = 34.sp, color = Color.White)
                RatingBar(
                    modifier = Modifier.height(50.dp),
                    rating = rating.toDouble()/2,
                    stars = 5,
                )
            }

        }
        IconButton(onClick = { onBackPressed() }) {
            Image(painter = painterResource(id = R.drawable.ic_back), contentDescription = "star")
        }
    }
}

@Composable
private fun TVShowSummary(summary: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp)) {
        if (summary.isNotEmpty()) {
            Text(modifier = Modifier.padding(bottom = 12.dp),
                fontSize = 20.sp, text = "Summary",
                color = MaterialTheme.colorScheme.primary)
            Text(fontSize = 14.sp, text = summary, color = Color.Gray, lineHeight = 14.sp)
        }

    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
) {

    val filledStars = kotlin.math.floor(rating).toInt()
    val unfilledStars = (stars - kotlin.math.ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier) {
        repeat(filledStars) {
            Image( painter = painterResource(id = R.drawable.ic_star), contentDescription = "star")
            Spacer(modifier = Modifier.width(4.dp))
        }

        if (halfStar) {
            Image(painter = painterResource(id = R.drawable.ic_half_star), contentDescription = "half star")
            Spacer(modifier = Modifier.width(4.dp))
        }

        repeat(unfilledStars) {
            Image(painter = painterResource(id = R.drawable.ic_unfilled_star), contentDescription = "unfilled star")
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}