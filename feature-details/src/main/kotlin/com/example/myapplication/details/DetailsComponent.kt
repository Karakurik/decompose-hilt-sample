package com.example.myapplication.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.example.myapplication.repository.Item
import com.example.myapplication.repository.Repository
import com.example.myapplication.repository.RepositoryModule
import dagger.Binds
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

interface DetailsComponent {
    val item: Value<Item>
    fun onCloseClicked()

    interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            itemId: String,
            onFinished: () -> Unit,
        ): DetailsComponent
    }
}

class DefaultDetailsComponent @AssistedInject internal constructor(
    repository: Repository,
    @Assisted componentContext: ComponentContext,
    @Assisted("itemId") itemId: String,
    @Assisted("onFinished") private val onFinished: () -> Unit,
) : DetailsComponent, ComponentContext by componentContext {

    override val item: Value<Item> = MutableValue(repository.getItem(id = itemId))

    override fun onCloseClicked() {
        onFinished()
    }

    @AssistedFactory
    interface Factory : DetailsComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            @Assisted("itemId") itemId: String,
            @Assisted("onFinished") onFinished: () -> Unit,
        ): DefaultDetailsComponent
    }
}
