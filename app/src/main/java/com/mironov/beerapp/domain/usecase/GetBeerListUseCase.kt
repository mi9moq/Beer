package com.mironov.beerapp.domain.usecase

import com.mironov.beerapp.domain.repository.BeerRepository

class GetBeerListUseCase (private val repository: BeerRepository) {

    suspend operator fun invoke() = repository.getList()
}