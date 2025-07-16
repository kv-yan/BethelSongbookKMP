package am.bethel.songbook

import am.bethel.application.common.data.factory.di.iosModule
import am.bethel.application.koin.initKoin
import am.bethel.application.navigation.navigation_component.RootComponent
import androidx.compose.ui.window.ComposeUIViewController
import androidx.compose.runtime.remember
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry

fun MainViewController() = ComposeUIViewController {
    val root = remember {
        RootComponent(DefaultComponentContext(LifecycleRegistry()))
    }
    initKoin(
        iosModule
    ){}
    App(root)
}