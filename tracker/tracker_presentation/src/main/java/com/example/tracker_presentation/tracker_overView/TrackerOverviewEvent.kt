package com.example.tracker_presentation.tracker_overView

import com.example.tracker_domain.model.TrackedFood

sealed class TrackerOverviewEvent {

    object OnNextDayClick: TrackerOverviewEvent()
    object OnPreviewDayClick: TrackerOverviewEvent()

    data class OnToggleMealClick(val meal: Meal): TrackerOverviewEvent()
    data class OnDeleteTrackedFood(val trackedFood: TrackedFood): TrackerOverviewEvent()
    data class OnAddFoodClick(val meal: Meal): TrackerOverviewEvent()

}