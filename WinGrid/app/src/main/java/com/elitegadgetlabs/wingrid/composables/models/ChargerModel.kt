package com.elitegadgetlabs.wingrid.composables.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChargerModel(
    val chargers: List<Charger>
)

@JsonClass(generateAdapter = true)
data class Charger(
    val name: String,
    val latitude: Double,
    val longitude: Double
)