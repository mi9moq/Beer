package com.mironov.beerapp.presentation.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mironov.beerapp.domain.entity.Result
import com.mironov.beerapp.domain.usecase.GetRandomBeerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RandomViewModel(
    private val getRandomBeerUseCase: GetRandomBeerUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<RandomScreenState>(RandomScreenState.Initial)
    val state = _state.asStateFlow()

    init {
        getRandomBeer()
    }

    fun getRandomBeer() {

        viewModelScope.launch {
            _state.value = RandomScreenState.Loading

            when (val result = getRandomBeerUseCase()) {
                is Result.Error -> {
                    _state.value = RandomScreenState.Error(result.errorType)
                }

                is Result.Success -> {
                    _state.value = RandomScreenState.Content(result.data)
                }
            }
        }
    }
}