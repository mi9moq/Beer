package com.mironov.beerapp.data.datasource

import com.mironov.beerapp.data.datasource
import com.mironov.beerapp.data.database.BeerDao
import com.mironov.beerapp.util.BeerData
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.mockito.kotlin.doReturn
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.verify

class BeerLocalDataSourceImplTest {
    private val dao: BeerDao = mock()
    private val dataSource = BeerLocalDataSourceImpl(dao)

    private val dbList = BeerData.dbList

    @Test
    fun `getList EXPECT get beer from dao`() = runTest {
        whenever(dao.getList()) doReturn dbList

        val expect = dbList
        val actual = dataSource.getList()

        assertEquals(expect, actual)
    }

    @Test
    fun `set EXPECT set beers to dao`() = runTest {

        dataSource.set(dbList)

        verify(dao).set(dbList)
    }
}