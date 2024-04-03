package com.example.myapplication.details

import com.example.myapplication.repository.RepositoryModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        RepositoryModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
interface DetailsModule {

    @Binds
    fun detailsFactory(impl: DefaultDetailsComponent.Factory): DetailsComponent.Factory
}
