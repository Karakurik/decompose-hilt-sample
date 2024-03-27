package com.example.myapplication.root

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.example.myapplication.details.DetailsContent
import com.example.myapplication.list.ListContent

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(animator = fade() + scale()),
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.ListChild -> ListContent(
                component = child.component,
                modifier = Modifier.fillMaxWidth()
            )

            is RootComponent.Child.DetailsChild -> DetailsContent(
                component = child.component,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
