package com.mironov.beerapp.presentation.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mironov.beerapp.domain.entity.Result
import com.mironov.beerapp.domain.usecase.GetBeerByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BeerInfoViewModel(
    private val useCase: GetBeerByIdUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<BeerInfoScreenState>(BeerInfoScreenState.Initial)
    val state = _state.asStateFlow()

    fun getById(id: Long) {
        _state.value = BeerInfoScreenState.Loading

        viewModelScope.launch {
            when (val result = useCase(id = id)) {
                is Result.Success -> {
                    _state.value = BeerInfoScreenState.Content(result.data)
                }

                is Result.Error -> {
                    _state.value = BeerInfoScreenState.Error(result.errorType)
                }
            }
        }
    }
}