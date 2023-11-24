package com.example.tracker_presentation.search

import com.example.tracker_domain.model.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val amount: String = "",
    val isExpended: Boolean = false
)
