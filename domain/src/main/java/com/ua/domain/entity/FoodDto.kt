package com.ua.domain.entity


import com.google.gson.annotations.SerializedName

data class FoodDto(
        @SerializedName("results") val results: ArrayList<FoodEntity>
)