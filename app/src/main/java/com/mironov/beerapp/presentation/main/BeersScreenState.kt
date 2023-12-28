package com.mironov.beerapp.presentation.main

import com.mironov.beerapp.domain.entity.Beer
import com.mironov.beerapp.domain.entity.ErrorType

sealed interface BeersScreenState {
    object Initial : BeersScreenState

    object Loading : BeersScreenState

    data class Content(val content: List<Beer>) : BeersScreenState

    data class Error(val errorType: ErrorType) : BeersScreenState
}