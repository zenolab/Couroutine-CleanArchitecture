package com.ua.domain.boundary.repository

import com.ua.domain.entity.FoodDto
import com.ua.domain.entity.FoodEntity


interface AppRepository{
    suspend fun getHome(): FoodDto
    suspend fun saveFoods(foodDto: FoodDto): Long
    suspend fun selectAllFoods() : MutableList<FoodEntity>
}