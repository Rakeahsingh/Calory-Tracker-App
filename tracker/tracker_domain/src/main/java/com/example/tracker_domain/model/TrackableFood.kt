package com.example.tracker_domain.model

data class TrackableFood(
    val name: String,
    val imageUrl: String?,
    val caloriesPer100gm: Int,
    val carbsPer100gm: Int,
    val proteinPer100gm: Int,
    val fatPer100gm: Int

)
