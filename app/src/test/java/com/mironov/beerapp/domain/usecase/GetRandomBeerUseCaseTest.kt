package com.mironov.beerapp.domain.usecase

import com.mironov.beerapp.domain.repository.BeerRepository
import com.mironov.beerapp.util.BeerData
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetRandomBeerUseCaseTest {

    private val repository: BeerRepository = mock()
    private val useCase = GetRandomBeerUseCase(repository)

    private val beer = BeerData.beer

    @Test
    fun `get random EXPECT beer`() = runTest {
        whenever(repository.getRandom()) doReturn beer

        val expected = beer
        val actual = useCase()

        assertEquals(expected, actual)
    }
}