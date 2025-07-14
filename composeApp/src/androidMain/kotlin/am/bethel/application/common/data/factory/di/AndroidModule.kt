package am.bethel.application.common.data.factory.di

import am.bethel.application.common.data.factory.DatabaseDriverFactory
import org.koin.dsl.module

val androidModule = module {
    single<DatabaseDriverFactory> {
        DatabaseDriverFactory(get())
    }
}