package com.mironov.beerapp.di

import com.mironov.beerapp.data.network.api.BeerApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://api.punkapi.com/v2/"

private fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

private fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

private fun provideBeerApi(retrofit: Retrofit): BeerApi =
    retrofit.create()

fun provideNetworkModule(): Module =
    module {
        single { provideOkHttpClient() }
        single { provideRetrofit(httpClient = get()) }
        single { provideBeerApi(retrofit = get()) }
    }