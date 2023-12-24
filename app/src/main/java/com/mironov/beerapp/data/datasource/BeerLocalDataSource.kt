package com.mironov.beerapp.data.datasource

import com.mironov.beerapp.data.database.BeerDao
import com.mironov.beerapp.data.database.model.BeerDbModel

interface BeerLocalDataSource {
    suspend fun getList(): List<BeerDbModel>

    suspend fun set(beerList: List<BeerDbModel>)
}

class BeerLocalDataSourceImpl(
    private val dao: BeerDao,
) : BeerLocalDataSource {
    override suspend fun getList(): List<BeerDbModel> =
        dao.getList()

    override suspend fun set(beerList: List<BeerDbModel>) =
        dao.set(beerList = beerList)
}