package com.architecture.clean.data.source.db

import androidx.room.*
import com.ua.domain.entity.FoodEntity


@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(foodEntity: FoodEntity): Long

    @Delete
   suspend fun deleteFood(foodEntity: FoodEntity): Int

    @Query("SELECT * from FoodEntity")
   suspend fun selectAllFoods(): MutableList<FoodEntity>

}