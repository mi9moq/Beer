package com.mironov.beerapp.data.repository

import com.mironov.beerapp.data.datasource.BeerLocalDataSource
import com.mironov.beerapp.data.datasource.BeerRemoteDataSource
import com.mironov.beerapp.data.mapper.BeerMapper
import com.mironov.beerapp.domain.entity.Result
import com.mironov.beerapp.util.BeerData
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.stream.Stream

class BeerRepositoryImplTest {

    private companion object {
        @JvmStatic
        fun connectionError(): Stream<Exception> = Stream.of(
            UnknownHostException(),
            ConnectException(),
            SocketTimeoutException()
        )
    }

    private val remoteDataSource: BeerRemoteDataSource = mock()
    private val localDataSource: BeerLocalDataSource = mock()
    private val mapper: BeerMapper = mock()
    private val dispatcher = StandardTestDispatcher()
    private val repository: BeerRepositoryImpl = BeerRepositoryImpl(
        dispatcher = dispatcher,
        localDataSource = localDataSource,
        remoteDataSource = remoteDataSource,
        mapper = mapper,
    )

    private val beer = BeerData.beer
    private val beerDto = BeerData.beerDto
    private val beerDbModel = BeerData.beerDbModel
    private val beerList = BeerData.beerList
    private val beerListDto = BeerData.dtoList
    private val beerListDbModel = BeerData.dbList

    private val unknownError = BeerData.unknownError
    private val connectionError = BeerData.connectionError

    @Test
    fun `get EXPECT beer list`() = runTest(dispatcher) {
        whenever(remoteDataSource.getList()) doReturn beerListDto
        whenever(localDataSource.getList()) doReturn beerListDbModel
        whenever(mapper.mapDtoToDb(beerDto)) doReturn beerDbModel
        whenever(mapper.mapDbToEntity(beerDbModel)) doReturn beer

        val expected = Result.Success(beerList)
        val actual = repository.getList()

        assertEquals(expected, actual)
    }

    @Test
    fun `get list EXPECT insert in DB`() = runTest(dispatcher) {
        whenever(remoteDataSource.getList()) doReturn beerListDto
        whenever(mapper.mapDtoToDb(beerDto)) doReturn beerDbModel

        repository.getList()

        verify(localDataSource).set(beerListDbModel)
    }

    @ParameterizedTest
    @MethodSource("connectionError")
    fun `get with Internet exception EXPECT connection error type`(e: Exception) =
        runTest(dispatcher) {
            whenever(remoteDataSource.getList()) doAnswer { throw e }

            val expected = connectionError
            val actual = repository.getList()

            assertEquals(expected, actual)
        }

    @Test
    fun `get with unknown exception EXPECT error type UNKNOWN`() = runTest(dispatcher) {
        whenever(remoteDataSource.getList()) doAnswer { throw Exception() }

        val expected = unknownError
        val actual = repository.getList()

        assertEquals(expected, actual)
    }

    @Test
    fun `get random EXPECT beer`() = runTest(dispatcher) {
        whenever(remoteDataSource.getRandom()) doReturn beerDto
        whenever(mapper.mapDtoToEntity(beerDto)) doReturn beer

        val expected = Result.Success(beer)
        val actual = repository.getRandom()

        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("connectionError")
    fun `get random with Internet exception EXPECT connection error type`(e: Exception) =
        runTest(dispatcher) {
            whenever(remoteDataSource.getRandom()) doAnswer { throw e }

            val expected = connectionError
            val actual = repository.getRandom()

            assertEquals(expected, actual)
        }

    @Test
    fun `get random with unknown exception EXPECT error type UNKNOWN`() = runTest(dispatcher) {
        whenever(remoteDataSource.getRandom()) doAnswer { throw Exception() }

        val expected = unknownError
        val actual = repository.getRandom()

        assertEquals(expected, actual)
    }

    @Test
    fun `get by id EXPECT beer`() = runTest(dispatcher) {
        whenever(remoteDataSource.getById(id = 1L)) doReturn beerDto
        whenever(mapper.mapDtoToEntity(beerDto)) doReturn beer

        val expected = Result.Success(beer)
        val actual = repository.getById(id = 1L)

        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("connectionError")
    fun `get by id with Internet exception EXPECT connection error type`(e: Exception) =
        runTest(dispatcher) {
            whenever(remoteDataSource.getById(id = 1L)) doAnswer { throw e }

            val expected = connectionError
            val actual = repository.getById(id = 1L)

            assertEquals(expected, actual)
        }

    @Test
    fun `get by id with unknown exception EXPECT error type UNKNOWN`() = runTest(dispatcher) {
        whenever(remoteDataSource.getById(id = 1L)) doAnswer { throw Exception() }

        val expected = unknownError
        val actual = repository.getById(id = 1L)

        assertEquals(expected, actual)
    }
}