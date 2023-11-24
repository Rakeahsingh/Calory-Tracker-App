package com.example.tracker_data.mapper

import com.example.tracker_data.local.entity.TrackerFoodEntity
import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackedFood
import java.time.LocalDate

fun TrackerFoodEntity.toTrackedFood(): TrackedFood{
    return TrackedFood(
        name = name,
        carbs = carb,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        mealType = MealType.fromString(type),
        amount = amount,
        date = LocalDate.of(year, month, dayOfMonth),
        calories = calories,
        id = id
    )
}

fun TrackedFood.toTrackerFoodEntity(): TrackerFoodEntity{
    return TrackerFoodEntity(
        name = name,
        carb = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        type = mealType.name,
        amount = amount,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        calories = calories,
        id = id
    )
}