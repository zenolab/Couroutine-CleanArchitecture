package com.ua.domain.interactor

import com.ua.domain.boundary.exception.ErrorMapper
import com.ua.domain.boundary.repository.AppRepository
import com.ua.domain.entity.FoodEntity
import com.ua.domain.interactor.base.UseCase
import javax.inject.Inject

class GetAllFoodsUseCase @Inject constructor(
    errorUtil: ErrorMapper,
    private val appRepository: AppRepository
) : UseCase<MutableList<FoodEntity>>(errorUtil) {

    override suspend fun executeOnBackground(): MutableList<FoodEntity> {
        return appRepository.selectAllFoods()
    }
}