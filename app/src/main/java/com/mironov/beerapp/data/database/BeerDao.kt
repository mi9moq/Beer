package com.mironov.beerapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mironov.beerapp.data.database.model.BeerDbModel

@Dao
interface BeerDao {
    @Query("SELECT * FROM ${BeerDbModel.TABLE_NAME}")
    suspend fun getList(): List<BeerDbModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun set(beerList: List<BeerDbModel>)
}