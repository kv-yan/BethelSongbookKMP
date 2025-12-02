package am.bethel.songbook

import am.bethel.application.common.data.factory.di.iosModule
import am.bethel.application.koin.initKoin
import am.bethel.application.navigation.navigation_component.RootComponent
import am.bethel.application.screen_awake.ScreenAwakeController
import am.bethel.application.share.di.screenAwakeModule
import am.bethel.application.share.di.shareIosModule
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry

fun MainViewController() = ComposeUIViewController {
    val root = remember {
        RootComponent(DefaultComponentContext(LifecycleRegistry()))
    }
    val screenAwakeController = ScreenAwakeController()
    initKoin(
        iosModule,
        shareIosModule,
        screenAwakeModule
    ) {}
    App(root) {
        screenAwakeController.keepScreenOn(it)
    }
}