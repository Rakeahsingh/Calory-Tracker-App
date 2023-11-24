package com.example.tracker_domain.use_case

import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository

class DeleteTrackFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
       trackedFood: TrackedFood
    ) {
         repository.deleteTrackedFood(trackedFood)
    }

}