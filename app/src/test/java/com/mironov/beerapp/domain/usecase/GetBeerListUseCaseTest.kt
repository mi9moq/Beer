package com.mironov.beerapp.domain.usecase

import com.mironov.beerapp.domain.repository.BeerRepository
import com.mironov.beerapp.util.BeerData
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetBeerListUseCaseTest {

    private val repository: BeerRepository = mock()
    private val useCase = GetBeerListUseCase(repository)

    private val beerList = BeerData.beerList

    @Test
    fun `invoke EXPECT list beer`() = runTest {
        whenever(repository.getList()) doReturn beerList

        val expected = beerList
        val actual = useCase()

        assertEquals(expected, actual)
    }
}