package com.mironov.beerapp.di

import com.mironov.beerapp.data.datasource.BeerLocalDataSource
import com.mironov.beerapp.data.datasource.BeerLocalDataSourceImpl
import com.mironov.beerapp.data.datasource.BeerRemoteDataSource
import com.mironov.beerapp.data.datasource.BeerRemoteDataSourceImpl
import com.mironov.beerapp.data.mapper.BeerMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

fun provideDataModule(): Module =
    module {
        factory<BeerMapper> { BeerMapper() }
        factory<BeerRemoteDataSource> { BeerRemoteDataSourceImpl(api = get()) }
        factory<BeerLocalDataSource> { BeerLocalDataSourceImpl(dao = get()) }
        single<CoroutineDispatcher> { Dispatchers.IO }
    }