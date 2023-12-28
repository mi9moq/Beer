package com.mironov.beerapp.presentation

import app.cash.turbine.test
import com.mironov.beerapp.domain.entity.ErrorType
import com.mironov.beerapp.domain.usecase.GetRandomBeerUseCase
import com.mironov.beerapp.presentation.random.RandomScreenState
import com.mironov.beerapp.presentation.random.RandomViewModel
import com.mironov.beerapp.util.BeerData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doSuspendableAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class RandomViewModelTest {

    private val getRandomBeerUseCase: GetRandomBeerUseCase = mock()
    private lateinit var viewModel: RandomViewModel

    private val unknownError = BeerData.unknownError
    private val connectionError = BeerData.connectionError
    private val beerSuccess = BeerData.beerSuccess
    private val beer = BeerData.beer

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        viewModel = RandomViewModel(getRandomBeerUseCase)
    }

    @Test
    fun `view model created EXPECT Initial state`() = runTest {
        viewModel.state.test {
            assertEquals(RandomScreenState.Initial, awaitItem())
        }
    }

    @Test
    fun `invoke view model getRandomBeer() EXPECT state changing from Initial to Loading`() =
        runTest {
            viewModel.state.test {
                viewModel.getRandomBeer()
                assertEquals(RandomScreenState.Initial, awaitItem())
                assertEquals(RandomScreenState.Loading, awaitItem())
            }
        }

    @Test
    fun `result return unknown error EXPECT state changing from Initial to Loading to Error`() =
        runTest {
            whenever(getRandomBeerUseCase()) doSuspendableAnswer {
                delay(1L)
                unknownError
            }

            viewModel.state.test {
                viewModel.getRandomBeer()
                assertEquals(RandomScreenState.Initial, awaitItem())
                assertEquals(RandomScreenState.Loading, awaitItem())
                assertEquals(RandomScreenState.Error(errorType = ErrorType.UNKNOWN), awaitItem())
            }
        }

    @Test
    fun `result return connection error EXPECT state changing from Initial to Loading to Error`() =
        runTest {
            whenever(getRandomBeerUseCase()) doSuspendableAnswer {
                delay(1L)
                connectionError
            }

            viewModel.state.test {
                viewModel.getRandomBeer()
                assertEquals(RandomScreenState.Initial, awaitItem())
                assertEquals(RandomScreenState.Loading, awaitItem())
                assertEquals(RandomScreenState.Error(errorType = ErrorType.CONNECTION), awaitItem())
            }
        }

    @Test
    fun `result return success EXPECT state changing from Initial to Loading to Content`() =
        runTest {
            whenever(getRandomBeerUseCase()) doSuspendableAnswer {
                delay(1L)
                beerSuccess
            }

            viewModel.state.test {
                viewModel.getRandomBeer()
                assertEquals(RandomScreenState.Initial, awaitItem())
                assertEquals(RandomScreenState.Loading, awaitItem())
                assertEquals(RandomScreenState.Content(content = beer), awaitItem())
            }
        }
}