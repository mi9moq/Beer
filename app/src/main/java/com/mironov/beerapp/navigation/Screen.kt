package com.mironov.beerapp.navigation

sealed class Screen(
    val route: String,
) {
    companion object {
        const val KEY_BEER_ID = "beer_id"

        private const val ROUTE_HOME = "home"
        private const val ROUTE_BEERS = "beers"
        private const val ROUTE_RANDOM_BEER = "random_beer"
        private const val ROUTE_BEER_INFO = "beer_info/{$KEY_BEER_ID}"
    }

    object Home : Screen(ROUTE_HOME)

    object Beers : Screen(ROUTE_BEERS)

    object Random : Screen(ROUTE_RANDOM_BEER)

    object Info : Screen(ROUTE_BEER_INFO) {

        private const val ROUTE_FOR_ARGS = "beer_info"

        fun getRouteWithArgs(beerId: String): String =
            "$ROUTE_FOR_ARGS/$beerId"
    }
}