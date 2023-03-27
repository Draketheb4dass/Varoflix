@file:JvmName("VaroflixAppKt")

package com.jephtecolin.varoflix

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jephtecolin.varoflix.ui.detail.MovieDetail
import com.jephtecolin.varoflix.ui.favorite.FavoriteScreen
import com.jephtecolin.varoflix.ui.home.HomeScreen
import com.jephtecolin.varoflix.ui.search.SearchScreen

@Composable
fun AppNavigation(
    appState: VaroflixAppState = rememberVaroflixAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { backStackEntry ->
            HomeScreen(
                viewModel = hiltViewModel(),
                navigateToMovie = { movieId ->
                    appState.navigateToMovie(
                        "${Screen.MovieDetail.route}/${movieId}",
                        backStackEntry
                    )
                },
                navigateToSearch = {
                    appState.navigateToSearch()
                },
                navigateToFavorite = {
                    appState.navigateToFavorite()
                }
            )
        }
        composable("${Screen.MovieDetail.route}/{movieId}" , arguments = listOf(navArgument("movieId"){
            type = NavType.LongType
        })) {
            MovieDetail(
                viewModel = hiltViewModel(),
                onBackPressed = { appState.navigateBack()})
        }

        composable(Screen.FavoriteMovie.route) {
            FavoriteScreen(
                viewModel = hiltViewModel(),
            onBackPressed = { appState.navigateBack()})
        }

        composable(Screen.SearchMovie.route) {
            SearchScreen(
                viewModel = hiltViewModel(),
                onBackPress = { appState.navigateBack()})
        }

    }
}