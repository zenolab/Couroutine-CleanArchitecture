package com.architecture.clean.ui.home

import androidx.lifecycle.MutableLiveData
import android.util.Log
import androidx.lifecycle.ViewModel
import com.ua.domain.boundary.error.ErrorModel
import com.ua.domain.boundary.error.ErrorStatus
import com.ua.domain.entity.FoodDto
import com.ua.domain.interactor.GetAllFoodsUseCase
import com.ua.domain.interactor.GetHomeUseCase
import com.ua.domain.interactor.InsertFoodsUseCase

import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase,
    private val insertFoodsUseCase: InsertFoodsUseCase,
    private val getAllFoodsUseCase: GetAllFoodsUseCase
) : ViewModel() {
    private val TAG = HomeViewModel::class.java.simpleName
    val homeData: MutableLiveData<FoodDto> by lazy { MutableLiveData<FoodDto>() }
    val error: MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }
    val foodsCount: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    init {

        getHomeUseCase.execute {
            onComplete {
                Log.d(TAG, it.toString())
                homeData.value = it
                insert(it)
            }

            onError { throwable ->
                if (throwable.errorStatus == ErrorStatus.UNAUTHORIZED) {
                    doReshresh()
                } else {
                    error.value = throwable
                }
            }

            onCancel {

            }
        }
    }

    private fun doReshresh() {

    }

    fun insert(foodDto: FoodDto) {
        insertFoodsUseCase.foodDto = foodDto
        insertFoodsUseCase.execute {

            onComplete { v1 ->
                {
                    returnFoodsInDb()
                }
            }

            onError { throwable ->
                error.value = throwable
            }

            onCancel {

            }
        }
    }

    private fun returnFoodsInDb() {
        getAllFoodsUseCase.execute {
            onComplete {
                foodsCount.value = it.size
            }
            onError {
                error.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        insertFoodsUseCase.unsubscribe()
        getHomeUseCase.unsubscribe()
        getAllFoodsUseCase.unsubscribe()
    }
}