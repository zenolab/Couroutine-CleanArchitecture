package com.ua.domain.boundary.exception

import com.ua.domain.boundary.error.ErrorModel


interface ErrorMapper {
    fun mapToDomainErrorException(throwable: Throwable?): ErrorModel
}