package com.example.myapplication.root

import com.example.myapplication.details.DetailsModule
import com.example.myapplication.list.ListModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        ListModule::class,
        DetailsModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
interface RootModule {

    @Binds
    fun rootFactory(impl: DefaultRootComponent.Factory): RootComponent.Factory
}
