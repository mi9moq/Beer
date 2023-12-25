package com.mironov.beerapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mironov.beerapp.data.database.converter.StringCollectionConverter
import com.mironov.beerapp.data.database.model.BeerDbModel

@Database(entities = [BeerDbModel::class], version = 1, exportSchema = false)
@TypeConverters(StringCollectionConverter::class)
abstract class BeerDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao
}