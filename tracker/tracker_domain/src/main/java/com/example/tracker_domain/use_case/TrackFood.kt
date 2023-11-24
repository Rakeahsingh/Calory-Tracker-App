package com.example.tracker_domain.use_case

import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

class TrackFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        food: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate
    ){

        repository.insertTrackedFood(
            TrackedFood(
                name = food.name,
                carbs = ((food.carbsPer100gm / 100f) * amount).roundToInt(),
                protein = ((food.proteinPer100gm / 100f) * amount).roundToInt(),
                fat = ((food.fatPer100gm / 100f) * amount).roundToInt(),
                calories = ((food.caloriesPer100gm / 100f) * amount).toInt(),
                mealType = mealType,
                imageUrl = food.imageUrl,
                amount = amount,
                date = date
            )
        )

    }

}