package com.mironov.beerapp.util

import com.mironov.beerapp.domain.entity.Beer

object BeerData {

    private val foodPairing = listOf("f1", "f2", "f3")

    val beer = Beer(
        id = 1L,
        name = "name",
        tagline = "tagline",
        description = "description",
        imageUrl = "imageUrl",
        abv = 1f,
        ibu = 1f,
        ebc = 1f,
        srm = 1f,
        ph = 1f,
        foodPairing = foodPairing,
    )

    val beerList = listOf(beer, beer, beer)
}