package am.bethel.songbook

import am.bethel.application.navigation.navigation_component.RootComponent
import am.bethel.application.screen_awake.ScreenAwakeController
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry

fun MainViewController() = ComposeUIViewController {
    val root = remember {
        RootComponent(DefaultComponentContext(LifecycleRegistry()))
    }
    val screenAwakeController = ScreenAwakeController()
    // TODO: check koin exception for ios
/*
    initKoin(
        iosModule,
        shareIosModule,
        screenAwakeModule
    ) {}
*/
    App(root) {
        screenAwakeController.keepScreenOn(it)
    }
}

/*
*
    @main
    struct iOSApp: App {

        init() {
            KoinKt.initKoin(
                iosModule,
                shareIosModule,
                screenAwakeModule
            )
        }

        var body: some Scene {
            WindowGroup {
                MainViewController()
            }
        }
    }
*
*/