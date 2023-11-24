package com.example.tracker_data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tracker_data.local.entity.TrackerFoodEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackedFood(trackerFoodEntity: TrackerFoodEntity)

    @Delete
    suspend fun deleteTrackedFood(trackerFoodEntity: TrackerFoodEntity)

    @Query(
        """
          SELECT * 
          FROM TRACKERFOODENTITY
          WHERE dayOfMonth = :day AND month = :month AND year = :year
        """
    )
    fun getFoodsForDay(day: Int, month: Int, year: Int): Flow<List<TrackerFoodEntity>>
}