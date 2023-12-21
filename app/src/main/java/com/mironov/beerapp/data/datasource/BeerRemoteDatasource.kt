package com.mironov.beerapp.data.datasource

import com.mironov.beerapp.data.network.api.BeerApi
import com.mironov.beerapp.data.network.model.BeerDto

interface BeerRemoteDatasource {
    suspend fun getList(): List<BeerDto>

    suspend fun getById(id: Long): BeerDto

    suspend fun getRandom(): BeerDto
}

class BeerRemoteDatasourceImpl(
    private val api: BeerApi,
) : BeerRemoteDatasource {
    override suspend fun getList(): List<BeerDto> =
        api.getList()

    override suspend fun getById(id: Long): BeerDto =
        api.getById(id = id)

    override suspend fun getRandom(): BeerDto =
        api.getRandom()
}