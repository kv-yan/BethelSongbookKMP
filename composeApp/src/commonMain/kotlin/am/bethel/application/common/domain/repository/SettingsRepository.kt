package am.bethel.application.common.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val themeIndexFlow: Flow<Int>
    val fontSizeFlow: Flow<Float>
    val dataVersionFlow: Flow<Int>

    suspend fun setThemeIndex(value: Int)
    suspend fun setFontSize(value: Float)
    suspend fun setDataVersion(value: Int)
}
