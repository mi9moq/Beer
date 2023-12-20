package com.mironov.beerapp.domain.repository

import com.mironov.beerapp.domain.entiry.Beer

interface BeerRepository {

    suspend fun getList() : List<Beer>

    suspend fun getById(id: Long): Beer

    suspend fun getRandom(): Beer
}