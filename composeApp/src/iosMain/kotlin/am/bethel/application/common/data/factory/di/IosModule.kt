package am.bethel.application.common.data.factory.di

import am.bethel.application.common.data.di.createDataStore
import am.bethel.application.common.data.factory.DatabaseDriverFactory
import org.koin.dsl.module

val iosModule = module {
    single<DatabaseDriverFactory> {
        DatabaseDriverFactory()
    }

    single {
        createDataStore()
    }
}