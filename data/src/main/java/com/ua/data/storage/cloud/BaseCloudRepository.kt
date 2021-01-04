package com.architecture.clean.data.source.cloud

import com.ua.domain.entity.FoodDto


interface BaseCloudRepository {
   suspend fun getHome(): FoodDto
}