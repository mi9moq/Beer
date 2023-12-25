package com.mironov.beerapp.data.network.model

import com.google.gson.annotations.SerializedName

data class BeerDto(
    val id: Long,
    val name: String,
    val tagline: String,
    val description: String,
    @SerializedName("image_url") val imageUrl: String,
    val abv: Float,
    val ibu: Float,
    val ebc: Float,
    val srm: Float,
    val ph: Float,
    @SerializedName("food_pairing") val foodPairing: List<String>,
)
