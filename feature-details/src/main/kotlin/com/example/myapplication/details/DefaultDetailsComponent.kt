package com.example.myapplication.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.example.myapplication.repository.Item
import com.example.myapplication.repository.Repository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DetailsComponent @AssistedInject internal constructor(
    repository: Repository,
    @Assisted componentContext: ComponentContext,
    @Assisted("itemId") itemId: String,
    @Assisted("onFinished") private val onFinished: () -> Unit,
) : ComponentContext by componentContext {

    val item: Value<Item> = MutableValue(repository.getItem(id = itemId))

    fun onCloseClicked() {
        onFinished()
    }

    @AssistedFactory
    interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            @Assisted("itemId") itemId: String,
            @Assisted("onFinished") onFinished: () -> Unit,
        ): DetailsComponent
    }
}
