package com.mironov.beerapp.domain.usecase

import com.mironov.beerapp.domain.repository.BeerRepository

class GetRandomBeerUseCase(private val repository: BeerRepository) {

    suspend operator fun invoke() = repository.getRandom()
}