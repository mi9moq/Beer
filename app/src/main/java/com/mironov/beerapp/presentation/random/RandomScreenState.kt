package com.mironov.beerapp.presentation.random

import com.mironov.beerapp.domain.entity.Beer
import com.mironov.beerapp.domain.entity.ErrorType

sealed interface RandomScreenState {

    object Initial : RandomScreenState

    object Loading : RandomScreenState

    data class Content(val content: Beer) : RandomScreenState

    data class Error(val errorType: ErrorType) : RandomScreenState
}