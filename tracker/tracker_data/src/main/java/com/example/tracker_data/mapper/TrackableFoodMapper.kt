package com.example.tracker_data.mapper

import com.example.tracker_data.remote.dto.Product
import com.example.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt

fun Product.toTrackableFood(): TrackableFood? {
    val carbsPer100gm = nutriments.carbohydrates100g.roundToInt()
    val proteinPer100gm = nutriments.proteins100g.roundToInt()
    val fatPer100gm = nutriments.fat100g.roundToInt()
    val caloriesPer100gm = nutriments.energyKcal100g.roundToInt()
    return TrackableFood(
        name = productName ?: return null,
        carbsPer100gm = carbsPer100gm,
        proteinPer100gm = proteinPer100gm,
        fatPer100gm = fatPer100gm,
        caloriesPer100gm = caloriesPer100gm,
        imageUrl = imageFrontThumbUrl
    )
}