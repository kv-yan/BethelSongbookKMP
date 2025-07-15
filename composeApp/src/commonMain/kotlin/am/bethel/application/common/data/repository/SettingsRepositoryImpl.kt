package am.bethel.application.common.data.repository

import am.bethel.application.common.data.preferences.PreferencesKeys
import am.bethel.application.common.domain.repository.SettingsRepository
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    val dataStore: DataStore<Preferences>
) : SettingsRepository {


    override val themeIndexFlow: Flow<Int> = dataStore.data.map { prefs ->
        prefs[PreferencesKeys.THEME_INDEX] ?: 0
    }

    override val fontSizeFlow: Flow<Float> = dataStore.data.map { prefs ->
        prefs[PreferencesKeys.FONT_SIZE] ?: 16f
    }

    override val isFirstLaunchFlow: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[PreferencesKeys.IS_FIRST_LAUNCH] ?: true
    }

    override suspend fun setThemeIndex(value: Int) {
        dataStore.edit { it[PreferencesKeys.THEME_INDEX] = value }
    }

    override suspend fun setFontSize(value: Float) {
        dataStore.edit { it[PreferencesKeys.FONT_SIZE] = value }
    }

    override suspend fun setIsFirstLaunch(value: Boolean) {
        dataStore.edit { it[PreferencesKeys.IS_FIRST_LAUNCH] = value }
    }
}
