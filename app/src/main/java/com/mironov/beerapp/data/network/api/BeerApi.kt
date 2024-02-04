package com.mironov.beerapp.data.network.api

import com.mironov.beerapp.data.network.model.BeerDto
import retrofit2.http.GET
import retrofit2.http.Path

interface BeerApi {

    @GET("beers")
    suspend fun getList(): List<BeerDto>

    @GET("beers/{id}")
    suspend fun getById(
        @Path("id") id: Long,
    ): List<BeerDto>

    @GET("beers/random")
    suspend fun getRandom(): List<BeerDto>
}