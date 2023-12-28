package com.mironov.beerapp.presentation.info

import com.mironov.beerapp.domain.entity.Beer
import com.mironov.beerapp.domain.entity.ErrorType

sealed interface BeerInfoScreenState {
    object Initial : BeerInfoScreenState

    object Loading : BeerInfoScreenState

    data class Content(val content: Beer) : BeerInfoScreenState

    data class Error(val errorType: ErrorType) : BeerInfoScreenState
}