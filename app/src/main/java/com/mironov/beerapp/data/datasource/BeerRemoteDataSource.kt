package com.mironov.beerapp.data.datasource

import com.mironov.beerapp.data.network.api.BeerApi
import com.mironov.beerapp.data.network.model.BeerDto

interface BeerRemoteDataSource {
    suspend fun getList(): List<BeerDto>

    suspend fun getById(id: Long): BeerDto

    suspend fun getRandom(): BeerDto
}

class BeerRemoteDataSourceImpl(
    private val api: BeerApi,
) : BeerRemoteDataSource {
    override suspend fun getList(): List<BeerDto> =
        api.getList()

    override suspend fun getById(id: Long): BeerDto =
        api.getById(id = id).first()

    override suspend fun getRandom(): BeerDto =
        api.getRandom().first()
}