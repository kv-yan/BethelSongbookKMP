package am.bethel.songbook

import am.bethel.application.common.data.factory.di.iosModule
import am.bethel.application.koin.initKoin
import am.bethel.application.navigation.navigation_component.RootComponent
import am.bethel.application.screen_awake.ScreenAwakeController
import am.bethel.application.share.di.screenAwakeModule
import am.bethel.application.share.di.shareIosModule
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
private var koinStarted = false

fun MainViewController() = run {
    if (!koinStarted) {
        koinStarted = true
        initKoin(iosModule, shareIosModule, screenAwakeModule) {}
    }

    ComposeUIViewController {
        val backDispatcher = remember { BackDispatcher() }

        val root = remember {
            RootComponent(
                DefaultComponentContext(
                    lifecycle = LifecycleRegistry(),
                    backHandler = backDispatcher
                )
            )
        }

        val screenAwakeController = ScreenAwakeController()

        PredictiveBackGestureOverlay(
            backDispatcher = backDispatcher,
            backIcon = { progress, _ ->
/*
                PredictiveBackGestureIcon(
                    imageVector = Icons.Default.ArrowBack,
                    progress = progress,
                )
*/
            },
            modifier = Modifier.fillMaxSize(),
        ) {
            App(root) {
                screenAwakeController.keepScreenOn(it)
            }
        }
    }
}