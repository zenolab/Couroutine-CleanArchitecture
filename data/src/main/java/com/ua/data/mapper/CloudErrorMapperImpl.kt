package com.architecture.clean.data.mapper

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.ua.domain.boundary.error.ErrorModel
import com.ua.domain.boundary.error.ErrorStatus
import com.ua.domain.boundary.exception.ErrorMapper
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class CloudErrorMapperImpl @Inject constructor(private val gson: Gson) : ErrorMapper {

    override fun mapToDomainErrorException(throwable: Throwable?): ErrorModel {
        val errorModel: ErrorModel? = when (throwable) {

            is HttpException -> {
                if (throwable.code() == 401) {
                    ErrorModel(ErrorStatus.UNAUTHORIZED)
                } else {
                    getHttpError(throwable.response().errorBody())
                }
            }

            is SocketTimeoutException -> {
                ErrorModel("TIME OUT!!",0,ErrorStatus.TIMEOUT)
            }

            is IOException -> {
                ErrorModel("CHECK CONNECTION",0,ErrorStatus.NO_CONNECTION)
            }

            is UnknownHostException -> {
                ErrorModel("CHECK CONNECTION",0,ErrorStatus.NO_CONNECTION)
            }
            else -> null
        }
        return errorModel!!
    }


    private fun getHttpError(body: ResponseBody?): ErrorModel {
        return try {
            val result = body?.string()
            val json = Gson().fromJson(result, JsonObject::class.java)
            ErrorModel(  json.toString(), 400, ErrorStatus.BAD_RESPONSE)
        } catch (e: Throwable) {
            e.printStackTrace()
            ErrorModel(ErrorStatus.NOT_DEFINED)
        }

    }
}