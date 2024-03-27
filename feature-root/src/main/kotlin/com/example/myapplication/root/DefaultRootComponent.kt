package com.example.myapplication.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.example.myapplication.details.DetailsComponent
import com.example.myapplication.list.ListComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable

class RootComponent @AssistedInject internal constructor(
    private val listFactory: ListComponent.Factory,
    private val detailsFactory: DetailsComponent.Factory,
    @Assisted componentContext: ComponentContext,
) : ComponentContext by componentContext {

    private val nav = StackNavigation<Config>()

    val stack: Value<ChildStack<*, Child>> =
        childStack(
            source = nav,
            initialConfiguration = Config.List,
            serializer = Config.serializer(),
            handleBackButton = true,
            childFactory = ::child,
        )

    private fun child(config: Config, context: ComponentContext): Child =
        when (config) {
            is Config.List -> Child.ListChild(listComponent(context))
            is Config.Details -> Child.DetailsChild(detailsComponent(context, config))
        }

    private fun listComponent(context: ComponentContext): ListComponent =
        listFactory(
            componentContext = context,
            onItemSelected = { nav.push(Config.Details(itemId = it)) },
        )

    private fun detailsComponent(
        context: ComponentContext,
        config: Config.Details,
    ): DetailsComponent =
        detailsFactory(
            componentContext = context,
            itemId = config.itemId,
            onFinished = nav::pop,
        )

    @Serializable
    private sealed interface Config {
        @Serializable
        data object List : Config

        @Serializable
        data class Details(val itemId: String) : Config
    }

    sealed class Child {
        class ListChild(val component: ListComponent) : Child()
        class DetailsChild(val component: DetailsComponent) : Child()
    }

    @AssistedFactory
    interface Factory {
        operator fun invoke(componentContext: ComponentContext): RootComponent
    }
}
