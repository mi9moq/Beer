package com.mironov.beerapp.domain.repository

import com.mironov.beerapp.domain.entity.Beer
import com.mironov.beerapp.domain.entity.Result

interface BeerRepository {

    suspend fun getList() : Result<List<Beer>>

    suspend fun getById(id: Long): Result<Beer>

    suspend fun getRandom(): Result<Beer>
}