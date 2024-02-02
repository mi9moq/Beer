package com.mironov.beerapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeScreenNavGraph(
    beersScreenContent: @Composable () -> Unit,
    infoScreenContent: @Composable (Long) -> Unit,
) {
    navigation(
        startDestination = Screen.Beers.route,
        route = Screen.Home.route
    ) {
        composable(
            route = Screen.Beers.route,
        ) {
            beersScreenContent()
        }

        composable(
            route = Screen.Info.route,
        ) {
            val beerId = it.arguments?.getString(Screen.KEY_BEER_ID) ?: "1"
            infoScreenContent(beerId.toLong())
        }
    }
}