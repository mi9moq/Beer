package com.mironov.beerapp.domain.usecase

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

    private val beerSuccess = BeerData.beerSuccess
    private val unknownError = BeerData.unknownError
    private val connectionError = BeerData.connectionError

    @Test
    fun `get random EXPECT success beer`() = runTest {
        whenever(repository.getRandom()) doReturn beerSuccess

        val expected = beerSuccess
        val actual = useCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `get random with some error EXPECT error type UNKNOWN`() = runTest {
        whenever(repository.getRandom()) doReturn unknownError

        val expected = unknownError
        val actual = useCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `get random with connection error EXPECT error type CONNECTION`() = runTest {
        whenever(repository.getRandom()) doReturn connectionError

        val expected = connectionError
        val actual = useCase()

        assertEquals(expected, actual)
    }
}