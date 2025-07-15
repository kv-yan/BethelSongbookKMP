package am.bethel.application.common.data.factory.di

import am.bethel.application.common.data.di.createDataStore
import am.bethel.application.common.data.factory.DatabaseDriverFactory
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val androidModule = module {
    single<DatabaseDriverFactory> {
        DatabaseDriverFactory(get())
    }

    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            scope = CoroutineScope(Dispatchers.IO),
            produceFile = { get<Context>().preferencesDataStoreFile("user_settings") }
        )
    }

    single {
        createDataStore(get<Context>())
    }
}