package com.example.myapplication.list

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
interface ListModule {

    @Binds
    fun listFactory(impl: DefaultListComponent.Factory): ListComponent.Factory
}
