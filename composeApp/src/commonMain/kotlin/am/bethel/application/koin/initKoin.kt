package am.bethel.application.koin

import am.bethel.application.list.presentation.di.listPresentationModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(vararg modules: Module) {
    startKoin {
        modules(
            listOf(
                appModule,
                listPresentationModule,

            ) + modules
        )
    }
}