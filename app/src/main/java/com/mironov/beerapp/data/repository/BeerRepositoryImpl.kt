package com.mironov.beerapp.data.repository

import com.mironov.beerapp.data.datasource.BeerLocalDataSource
import com.mironov.beerapp.data.datasource.BeerRemoteDataSource
import com.mironov.beerapp.data.mapper.BeerMapper
import com.mironov.beerapp.domain.entity.Beer
import com.mironov.beerapp.domain.entity.ErrorType
import com.mironov.beerapp.domain.entity.Result
import com.mironov.beerapp.domain.repository.BeerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class BeerRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val mapper: BeerMapper,
    private val localDataSource: BeerLocalDataSource,
    private val remoteDataSource: BeerRemoteDataSource,
) : BeerRepository {

    override suspend fun getList(): Result<List<Beer>> =
        try {
            withContext(dispatcher) {
                val listFromRemote = remoteDataSource.getList()
                localDataSource.set(listFromRemote.map(mapper::mapDtoToDb))

                val localList = localDataSource.getList().map(mapper::mapDbToEntity)
                return@withContext Result.Success(localList)
            }
        } catch (t: Throwable) {
            when (t) {
                is UnknownHostException, is SocketTimeoutException, is ConnectException -> {
                    Result.Error(errorType = ErrorType.CONNECTION)
                }

                else -> {
                    Result.Error(errorType = ErrorType.UNKNOWN)
                }
            }
        }

    override suspend fun getById(id: Long): Result<Beer> =
        try {
            withContext(dispatcher) {
                val beer = remoteDataSource.getById(id = id).let(mapper::mapDtoToEntity)
                return@withContext Result.Success(beer)
            }
        } catch (t: Throwable) {
            when (t) {
                is UnknownHostException, is SocketTimeoutException, is ConnectException -> {
                    Result.Error(errorType = ErrorType.CONNECTION)
                }

                else -> {
                    Result.Error(errorType = ErrorType.UNKNOWN)
                }
            }
        }

    override suspend fun getRandom(): Result<Beer> =
        try {
            withContext(dispatcher) {
                val beer = remoteDataSource.getRandom().let(mapper::mapDtoToEntity)
                return@withContext Result.Success(beer)
            }
        } catch (t: Throwable) {
            when (t) {
                is UnknownHostException, is SocketTimeoutException, is ConnectException -> {
                    Result.Error(errorType = ErrorType.CONNECTION)
                }

                else -> {
                    Result.Error(errorType = ErrorType.UNKNOWN)
                }
            }
        }
}