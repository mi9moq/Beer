package com.mironov.beerapp.presentation

import androidx.lifecycle.Observer
import com.mironov.beerapp.domain.entity.ErrorType
import com.mironov.beerapp.domain.entity.Result
import com.mironov.beerapp.domain.usecase.GetBeerListUseCase
import com.mironov.beerapp.presentation.main.BeersScreenState
import com.mironov.beerapp.presentation.main.BeersViewModel
import com.mironov.beerapp.util.BeerData
import com.mironov.beerapp.util.BeerData.unknownError
import com.mironov.beerapp.util.MainDispatcherRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class BeersViewModelTest {

    private val getBeerListUseCase: GetBeerListUseCase = mock()
    private val viewModel: BeersViewModel = BeersViewModel(getBeerListUseCase)

    @Test
    fun `view model created EXPECT Initial state`() = runTest {
        assertEquals(BeersScreenState.Initial, viewModel.state.value)
    }

    @Test
    fun `getList return unknown error EXPECT Error state`() = runTest {
        Dispatchers.setMain(Dispatchers.Unconfined)
        whenever(getBeerListUseCase()) doReturn unknownError

        viewModel.getList()

        val expected = BeersScreenState.Error(errorType = ErrorType.UNKNOWN)
        val actual = viewModel.state.value

        assertEquals(expected, actual)
    }
}