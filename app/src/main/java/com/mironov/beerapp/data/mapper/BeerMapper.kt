package com.mironov.beerapp.data.mapper

import com.mironov.beerapp.data.database.model.BeerDbModel
import com.mironov.beerapp.data.network.model.BeerDto
import com.mironov.beerapp.domain.entity.Beer

class BeerMapper {

    fun mapDtoToDb(from: BeerDto): BeerDbModel = BeerDbModel(
        id = from.id,
        name = from.name,
        tagline = from.tagline,
        description = from.description,
        imageUrl = from.imageUrl,
        abv = from.abv,
        ibu = from.ibu,
        ebc = from.ebc,
        srm = from.srm,
        ph = from.ph,
        foodPairing = from.foodPairing,
    )

    fun mapDtoToEntity(from: BeerDto): Beer = Beer(
        id = from.id,
        name = from.name,
        tagline = from.tagline,
        description = from.description,
        imageUrl = from.imageUrl,
        abv = from.abv,
        ibu = from.ibu,
        ebc = from.ebc,
        srm = from.srm,
        ph = from.ph,
        foodPairing = from.foodPairing,
    )

    fun mapDbToEntity(from: BeerDbModel): Beer = Beer(
        id = from.id,
        name = from.name,
        tagline = from.tagline,
        description = from.description,
        imageUrl = from.imageUrl,
        abv = from.abv,
        ibu = from.ibu,
        ebc = from.ebc,
        srm = from.srm,
        ph = from.ph,
        foodPairing = from.foodPairing,
    )
}