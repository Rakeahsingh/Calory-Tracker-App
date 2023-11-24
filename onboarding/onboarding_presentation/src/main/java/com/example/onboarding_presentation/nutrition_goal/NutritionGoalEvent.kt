package com.example.onboarding_presentation.nutrition_goal

sealed class NutritionGoalEvent {

    data class onCarbRatioEnter(val ratio: String): NutritionGoalEvent()
    data class onProteinRatioEnter(val ratio: String): NutritionGoalEvent()
    data class onFatRatioEnter(val ratio: String): NutritionGoalEvent()

    object OnNextClick: NutritionGoalEvent()

}