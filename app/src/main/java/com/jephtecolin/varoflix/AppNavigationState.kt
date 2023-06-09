package com.jephtecolin.varoflix

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object MovieDetail : Screen("detail") {
        fun createRoute(movieId: String) = movieId
    }
    object FavoriteMovie : Screen("favorite_movie")
    object SearchMovie : Screen("search_movie")
}

@Composable
fun rememberVaroflixAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    VaroflixAppState(navController, context)
}


class VaroflixAppState(
    val navController: NavHostController,
    private val context: Context
) {
    fun navigateToMovie(movieUri: String, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.MovieDetail.createRoute(movieUri))
        }
    }

    fun navigateToFavorite() {
        navController.navigate(Screen.FavoriteMovie.route)
    }

    fun navigateToSearch() {
        navController.navigate(Screen.SearchMovie.route)
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.getLifecycle().currentState == Lifecycle.State.RESUMED