package com.mironov.beerapp.data.datasource

import com.mironov.beerapp.data.datasource
import com.mironov.beerapp.data.network.BeerApi
import com.mironov.beerapp.util.BeerData
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class BeerRemoteDataSourceImplTest {

    private val api: BeerApi = mock()
    private val dataSource = BeerRemoteDataSourceImpl(api)
    private val dtoList = BeerData.dtoList
    private val dto = BeerData.beerDto

    @Test
    fun `getList EXPECT beer list`() = runTest {
        whenever(api.getList()) doReturn dtoList

        val expected = dtoList
        val actual = dataSource.getList()

        assertEquals(expected, actual)
    }
}