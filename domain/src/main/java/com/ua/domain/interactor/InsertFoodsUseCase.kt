package com.ua.domain.interactor

import com.ua.domain.boundary.exception.ErrorMapper
import com.ua.domain.boundary.repository.AppRepository
import com.ua.domain.entity.FoodDto
import com.ua.domain.interactor.base.UseCase
import javax.inject.Inject

class InsertFoodsUseCase @Inject constructor(
    errorUtilImpl: ErrorMapper,
    private val appRepository: AppRepository
) : UseCase<Long>(errorUtilImpl) {

    var foodDto = FoodDto(arrayListOf())

    override suspend fun executeOnBackground(): Long {
        return appRepository.saveFoods(foodDto)
    }

}