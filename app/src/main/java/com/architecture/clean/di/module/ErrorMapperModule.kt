package com.architecture.clean.di.module

import com.architecture.clean.data.mapper.CloudErrorMapperImpl
import com.architecture.clean.domain.boundary.exception.ErrorMapper
import com.fedetto.daggermodulesdemo.di.annotation.scope.AppScope
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ErrorMapperModule {


    @Binds
    @Singleton
    abstract fun bindCustomRepository(instance: CloudErrorMapperImpl): ErrorMapper



}