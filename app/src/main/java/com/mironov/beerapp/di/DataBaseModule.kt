package com.mironov.beerapp.di

import androidx.room.Room
import com.mironov.beerapp.data.database.BeerDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

private const val DB_NAME = "beer_list.db"

fun provideDatabaseModule() : Module =
    module {
        single { Room.databaseBuilder(context = get(), BeerDatabase::class.java, DB_NAME).build() }
        single { get<BeerDatabase>().beerDao() }
    }