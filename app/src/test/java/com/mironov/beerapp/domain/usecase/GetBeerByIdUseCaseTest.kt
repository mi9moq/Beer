package com.mironov.beerapp.domain.usecase

import com.mironov.beerapp.domain.repository.BeerRepository
import com.mironov.beerapp.domain.usecase.GetBeerByIdUseCase
import com.mironov.beerapp.util.BeerData
import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.doReturn
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetBeerByIdUseCaseTest {

    private val repository: BeerRepository = mock()
    private val useCase = GetBeerByIdUseCase(repository)

    private val beer = BeerData.beer

    @Test
    fun `invoke EXPECT beer`() = runTest {
        whenever(repository.getById(id = 1L)) doReturn beer

        val expected = beer
        val actual = useCase()

        assertEquals(expected, actual)
    }
}