package com.mironov.beerapp.data.repository

import com.mironov.beerapp.data.database.BeerDao
import com.mironov.beerapp.data.datasource.BeerLocalDataSource
import com.mironov.beerapp.data.datasource.BeerRemoteDataSource
import com.mironov.beerapp.data.mapper.BeerMapper
import com.mironov.beerapp.data.network.api.BeerApi
import com.mironov.beerapp.domain.entity.Result
import com.mironov.beerapp.util.BeerData
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class BeerRepositoryImplTest {

    private val remoteDataSource: BeerRemoteDataSource = mock()
    private val localDataSource: BeerLocalDataSource = mock()
    private val dao: BeerDao = mock()
    private val api: BeerApi = mock()
    private val mapper: BeerMapper = mock()
    private val dispatcher = StandardTestDispatcher()
    private lateinit var repository: BeerRepositoryImpl

    private val beer = BeerData.beer
    private val beerDto = BeerData.beerDto
    private val beerDbModel = BeerData.beerDbModel
    private val beerList = BeerData.beerList
    private val beerListDto = BeerData.dtoList
    private val beerListDbModel = BeerData.dbList

    @Before
    fun setUp() {
        repository = BeerRepositoryImpl(
            dispatcher = dispatcher,
            beerDao = dao,
            beerApi = api,
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            mapper = mapper,
        )
    }

    @Test
    @Suppress("ktlint:standard:max-line-length")
    fun `get EXPECT beer list`() = runTest(dispatcher) {
        whenever(remoteDataSource.getList()) doReturn beerListDto
        whenever(mapper.mapDtoToDb(beerDto)) doReturn beerDbModel
        whenever(mapper.mapDbToEntity(beerDbModel)) doReturn beer

        val expected = Result.Success(beer)
        val actual = repository.getList()

        assertEquals(expected, actual)
    }
}