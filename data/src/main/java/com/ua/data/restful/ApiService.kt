package com.architecture.clean.data.restful


import com.ua.domain.entity.FoodDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("api/")
    fun getHome(
    ): Deferred<FoodDto>


}