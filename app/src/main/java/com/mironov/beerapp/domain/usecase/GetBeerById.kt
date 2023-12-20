package com.mironov.beerapp.domain.usecase

import com.mironov.beerapp.domain.repository.BeerRepository

class GetBeerById (private val repository: BeerRepository) {

    suspend operator fun invoke(id: Long) = repository.getById(id)
}