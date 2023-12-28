package com.mironov.beerapp.presentation

import app.cash.turbine.test
import com.mironov.beerapp.domain.entity.ErrorType
import com.mironov.beerapp.domain.usecase.GetBeerByIdUseCase
import com.mironov.beerapp.presentation.info.BeerInfoScreenState
import com.mironov.beerapp.presentation.info.BeerInfoViewModel
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
class BeerInfoViewModelTest {

    private val getBeerByIdUseCase: GetBeerByIdUseCase = mock()
    private val viewModel = BeerInfoViewModel(getBeerByIdUseCase)

    private val unknownError = BeerData.unknownError
    private val connectionError = BeerData.connectionError
    private val beerSuccess = BeerData.beerSuccess
    private val beer = BeerData.beer
    private val id = 1L

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `view model created EXPECT Initial state`() = runTest {
        assertEquals(BeerInfoScreenState.Initial, viewModel.state.value)
    }

    @Test
    fun `getById EXPECT Content state`() = runTest {
        whenever(getBeerByIdUseCase(id)) doSuspendableAnswer {
            delay(1L)
            beerSuccess
        }

        viewModel.state.test {
            viewModel.getById(id)
            assertEquals(BeerInfoScreenState.Initial, awaitItem())
            assertEquals(BeerInfoScreenState.Loading, awaitItem())
            assertEquals(BeerInfoScreenState.Content(beer), awaitItem())
        }
    }

    @Test
    fun `getById EXPECT Unknown Error State`() = runTest {
        whenever(getBeerByIdUseCase(id)) doSuspendableAnswer {
            delay(1L)
            unknownError
        }

        viewModel.state.test {
            viewModel.getById(id)
            assertEquals(BeerInfoScreenState.Initial, awaitItem())
            assertEquals(BeerInfoScreenState.Loading, awaitItem())
            assertEquals(BeerInfoScreenState.Error(ErrorType.UNKNOWN), awaitItem())
        }
    }

    @Test
    fun `getById EXPECT Connection Error State`() = runTest {
        whenever(getBeerByIdUseCase(id)) doSuspendableAnswer {
            delay(1L)
            connectionError
        }

        viewModel.state.test {
            viewModel.getById(id)
            assertEquals(BeerInfoScreenState.Initial, awaitItem())
            assertEquals(BeerInfoScreenState.Loading, awaitItem())
            assertEquals(BeerInfoScreenState.Error(ErrorType.CONNECTION), awaitItem())
        }
    }
}