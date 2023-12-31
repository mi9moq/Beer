package com.mironov.beerapp.navigation

import com.mironov.beerapp.domain.entity.Beer

sealed class Screen(
    val route: String,
) {
    companion object {
        private const val ROUTE_BEERS = "beers"
        private const val ROUTE_RANDOM_BEER = "random_beer"
        private const val ROUTE_BEER_INFO = "beer_info"
    }

    object Beers : Screen(ROUTE_BEERS)

    object Random : Screen(ROUTE_RANDOM_BEER)

    object Info : Screen(ROUTE_BEER_INFO) {
        fun getRouteWithArgs(beer: Beer): String =
            "$ROUTE_BEER_INFO/${beer.id}"
    }
}