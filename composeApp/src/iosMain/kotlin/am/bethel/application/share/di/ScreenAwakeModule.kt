package am.bethel.application.share.di

import am.bethel.application.screen_awake.ScreenAwakeController
import org.koin.dsl.module

val screenAwakeModule = module {
    single<ScreenAwakeController> {
        ScreenAwakeController()
    }
}