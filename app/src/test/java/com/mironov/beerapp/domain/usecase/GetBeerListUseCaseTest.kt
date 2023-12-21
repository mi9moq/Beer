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

    private val beerListSuccess = BeerData.beerListSuccess
    private val unknownError = BeerData.unknownError
    private val connectionError = BeerData.connectionError

    @Test
    fun `invoke EXPECT success list beer`() = runTest {
        whenever(repository.getList()) doReturn beerListSuccess

        val expected = beerListSuccess
        val actual = useCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `invoke with some error EXPECT error type UNKNOWN`() = runTest {
        whenever(repository.getList()) doReturn unknownError

        val expected = unknownError
        val actual = useCase()

        assertEquals(expected, actual)
    }

    @Test
    fun `invoke with connection error EXPECT error type CONNECTION`() = runTest {
        whenever(repository.getList()) doReturn connectionError

        val expected = connectionError
        val actual = useCase()

        assertEquals(expected, actual)
    }
}