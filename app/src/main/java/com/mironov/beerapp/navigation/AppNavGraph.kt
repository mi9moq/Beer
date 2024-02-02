package com.mironov.beerapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    beersScreenContent: @Composable () -> Unit,
    infoScreenContent: @Composable (Long) -> Unit,
    randomScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
    ) {

        homeScreenNavGraph(
            beersScreenContent = beersScreenContent,
            infoScreenContent = infoScreenContent,
        )

        composable(
            route = Screen.Random.route,
        ) {
            randomScreenContent()
        }
    }
}