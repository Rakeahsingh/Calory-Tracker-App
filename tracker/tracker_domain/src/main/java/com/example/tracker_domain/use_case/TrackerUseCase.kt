package com.example.tracker_domain.use_case

data class TrackerUseCase(
    val trackFood: TrackFood,
    val searchFood: SearchFood,
    val getFoodsForDate: GetFoodsForDate,
    val deleteTrackFood: DeleteTrackFood,
    val calculateMealNutrients: CalculateMealNutrients
)
