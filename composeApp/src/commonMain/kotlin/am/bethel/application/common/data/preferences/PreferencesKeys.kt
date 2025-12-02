package am.bethel.application.common.data.preferences

import androidx.datastore.preferences.core.*

object PreferencesKeys {
    val THEME_INDEX = intPreferencesKey("theme_index")
    val FONT_SIZE = floatPreferencesKey("font_size")
    val DB_VERSION = intPreferencesKey("db_version")
    val SCREEN_KEEP_AWAKE = booleanPreferencesKey("screen_keep_awake")
}
