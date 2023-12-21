package com.mironov.beerapp.domain.usecase

import com.mironov.beerapp.domain.entity.ErrorType
import com.mironov.beerapp.domain.entity.Result
import com.mironov.beerapp.domain.repository.BeerRepository
import com.mironov.beerapp.util.BeerData
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetRandomBeerUseCaseTest {

    private val repository: BeerRepository = mock()
    private val useCase = GetRandomBeerUseCase(repository)

    private val beer = BeerData.beer

    @Test
    fun `get random EXPECT success beer`() = runTest {
        whenever(repository.getRandom()) doReturn Result.Success(beer)

        val expected = Result.Success(beer)
        val actual = useCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `get random with some error EXPECT error type UNKNOWN`() = runTest {
        whenever(repository.getRandom()) doReturn Result.Error(errorType = ErrorType.UNKNOWN)

        val expected = Result.Error(errorType = ErrorType.UNKNOWN)
        val actual = useCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `get random with connection error EXPECT error type CONNECTION`() = runTest {
        whenever(repository.getRandom()) doReturn Result.Error(errorType = ErrorType.CONNECTION)

        val expected = Result.Error(errorType = ErrorType.CONNECTION)
        val actual = useCase()

        assertEquals(expected, actual)
    }
}