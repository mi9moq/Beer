package com.mironov.beerapp.presentation

import com.mironov.beerapp.domain.usecase.GetBeerListUseCase
import com.mironov.beerapp.presentation.main.BeersScreenState
import com.mironov.beerapp.presentation.main.BeersViewModel
import com.mironov.beerapp.util.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import kotlin.test.assertEquals

class BeersViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getBeerListUseCase: GetBeerListUseCase = mock()
    private val viewModel: BeersViewModel  = BeersViewModel(getBeerListUseCase)

    @Test
    fun `view model created EXPECT Initial state`() = runTest {
        assertEquals(BeersScreenState.Initial, viewModel.state.value)
    }
}