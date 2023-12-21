package com.mironov.beerapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mironov.beerapp.data.database.model.BeerDbModel

@Database(entities = [BeerDbModel::class], version = 1, exportSchema = false)
abstract class BeerDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao
}