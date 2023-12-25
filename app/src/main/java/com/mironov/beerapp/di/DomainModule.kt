package com.mironov.beerapp.di

import com.mironov.beerapp.data.datasource.BeerLocalDataSource
import com.mironov.beerapp.data.datasource.BeerRemoteDataSource
import com.mironov.beerapp.data.mapper.BeerMapper
import com.mironov.beerapp.data.repository.BeerRepositoryImpl
import com.mironov.beerapp.domain.repository.BeerRepository
import com.mironov.beerapp.domain.usecase.GetBeerByIdUseCase
import com.mironov.beerapp.domain.usecase.GetBeerListUseCase
import com.mironov.beerapp.domain.usecase.GetRandomBeerUseCase
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

private fun provideBeerRepository(
    dispatcher: CoroutineDispatcher,
    mapper: BeerMapper,
    localDataSource: BeerLocalDataSource,
    remoteDataSource: BeerRemoteDataSource,
): BeerRepository = BeerRepositoryImpl(
    dispatcher = dispatcher,
    mapper = mapper,
    localDataSource = localDataSource,
    remoteDataSource = remoteDataSource
)

fun provideDomainModule(): Module =
    module {
        single {
            provideBeerRepository(
                dispatcher = get(),
                mapper = get(),
                localDataSource = get(),
                remoteDataSource = get()
            )
        }

        factory { GetBeerByIdUseCase(repository = get()) }
        factory { GetBeerListUseCase(repository = get()) }
        factory { GetRandomBeerUseCase(repository = get()) }
    }