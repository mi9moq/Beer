package com.mironov.beerapp.presentation

import app.cash.turbine.test
import com.mironov.beerapp.domain.entity.ErrorType
import com.mironov.beerapp.domain.usecase.GetBeerListUseCase
import com.mironov.beerapp.presentation.main.BeersScreenState
import com.mironov.beerapp.presentation.main.BeersViewModel
import com.mironov.beerapp.util.BeerData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doSuspendableAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class BeersViewModelTest {

    private val getBeerListUseCase: GetBeerListUseCase = mock()
    private val viewModel: BeersViewModel = BeersViewModel(getBeerListUseCase)

    private val unknownError = BeerData.unknownError
    private val beerListSuccess = BeerData.beerListSuccess

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `view model created EXPECT Initial state`() = runTest {
        assertEquals(BeersScreenState.Initial, viewModel.state.value)
    }

    @Test
    fun `get beer EXPECT content state`() = runTest {
        whenever(getBeerListUseCase()) doSuspendableAnswer {
            delay(1L)
            beerListSuccess
        }

        viewModel.state.test {
            viewModel.getList()
            assertEquals(BeersScreenState.Initial, awaitItem())
            assertEquals(BeersScreenState.Loading, awaitItem())
            assertEquals(BeersScreenState.Content(beerListSuccess.data), awaitItem())
        }
    }

    @Test
    fun `getList return unknown error EXPECT Error state`() = runTest {
        whenever(getBeerListUseCase()) doSuspendableAnswer {
            delay(1L)
            unknownError
        }

        viewModel.state.test {
            viewModel.getList()
            assertEquals(BeersScreenState.Initial, awaitItem())
            assertEquals(BeersScreenState.Loading, awaitItem())
            assertEquals(BeersScreenState.Error(ErrorType.UNKNOWN), awaitItem())
        }
    }
}