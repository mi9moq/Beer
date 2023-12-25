package com.mironov.beerapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mironov.beerapp.domain.entity.Result
import com.mironov.beerapp.domain.usecase.GetBeerListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BeersViewModel(
    private val useCase: GetBeerListUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<BeersScreenState>(BeersScreenState.Initial)
    val state = _state.asStateFlow()

    fun getList() {
        _state.value = BeersScreenState.Loading

        viewModelScope.launch {
            when (val result = useCase()) {
                is Result.Success -> {
                    _state.value = BeersScreenState.Content(result.data)
                }

                is Result.Error -> {
                    _state.value = BeersScreenState.Error(result.errorType)
                }
            }
        }
    }
}