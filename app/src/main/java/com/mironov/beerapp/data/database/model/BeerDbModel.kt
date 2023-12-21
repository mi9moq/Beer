package com.mironov.beerapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = BeerDbModel.TABLE_NAME)
data class BeerDbModel(
    @PrimaryKey val id: Long,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String,
    val abv: Float,
    val ibu: Float,
    val ebc: Float,
    val srm: Float,
    val ph: Float,
    val foodPairing: List<String>,
) {
    companion object {
        const val TABLE_NAME = "beers"
    }
}
