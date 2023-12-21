package com.mironov.beerapp.util

import com.mironov.beerapp.domain.entity.Beer
import com.mironov.beerapp.domain.entity.ErrorType
import com.mironov.beerapp.domain.entity.Result

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

     val beerDto = BeerDto(
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
    val beerDbModel = BeerDbModel(
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


    private val beerList = listOf(beer, beer, beer)

    val unknownError = Result.Error(errorType = ErrorType.UNKNOWN)
    val connectionError = Result.Error(errorType = ErrorType.CONNECTION)

    val beerSuccess = Result.Success(beer)
    val beerListSuccess = Result.Success(beerList)
}