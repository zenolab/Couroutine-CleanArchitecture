package com.architecture.clean.data.source.cloud

import com.architecture.clean.data.restful.ApiService
import com.ua.domain.entity.FoodDto


class CloudRepository(private val apIs: ApiService) : BaseCloudRepository {
    override suspend fun getHome(): FoodDto {
        return apIs.getHome().await()
    }
}
