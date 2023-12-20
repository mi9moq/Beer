package com.mironov.beerapp.domain.entity

data class Beer(
    val id: Long,
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
)
