package am.bethel.application.common.data.preferences

import androidx.datastore.preferences.core.*

object PreferencesKeys {
    val THEME_INDEX = intPreferencesKey("theme_index")
    val FONT_SIZE = floatPreferencesKey("font_size")
    val IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
}
