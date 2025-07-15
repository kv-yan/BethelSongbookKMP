package am.bethel.application.settings.presentation

import am.bethel.application.settings.domain.model.AppTheme
import am.bethel.application.settings.domain.usecase.ChangeFontSizeUseCase
import am.bethel.application.settings.domain.usecase.ChangeThemeIndexUseCase
import am.bethel.application.settings.domain.usecase.GetFontSizeUseCase
import am.bethel.application.settings.domain.usecase.GetThemeIndexUseCase
import am.bethel.application.settings.domain.usecase.InsertAllSongToDbUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SettingsViewModel(
    getFontSizeUseCase: GetFontSizeUseCase,
    private val changeFontSizeUseCase: ChangeFontSizeUseCase,
    getThemeIndexUseCase: GetThemeIndexUseCase,
    private val changeThemeIndexUseCase: ChangeThemeIndexUseCase,
    insertAllSongToDbUseCase: InsertAllSongToDbUseCase

) : KoinComponent {

    val viewModelScope = CoroutineScope(Dispatchers.IO)

    private val _fontSize = MutableStateFlow(16f)
    val fontSize: StateFlow<Float> = _fontSize

    private val _appTheme = MutableStateFlow<AppTheme?>(null)
    val uiSettings = _appTheme.asStateFlow()

    private val _availableThemes = MutableStateFlow<List<AppTheme>>(AppTheme.entries)
    val availableThemes = _availableThemes.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            insertAllSongToDbUseCase()
        }

        getFontSizeUseCase().onEach {
            _fontSize.value = it
            println("fontSize: $it")
        }.launchIn(viewModelScope)

        getThemeIndexUseCase().onEach {
            _appTheme.value = _availableThemes.value[it]
            println("themeIndex: $it")
        }.launchIn(viewModelScope)

    }

    fun increment() {
        viewModelScope.launch {
            val newSize = (_fontSize.value + 1f).coerceAtMost(30f)
            changeFontSizeUseCase(newSize)
        }
    }

    fun decrement() {
        viewModelScope.launch {
            val newSize = (_fontSize.value - 1f).coerceAtLeast(10f)
            changeFontSizeUseCase(newSize)
        }
    }


    fun setUiSetting(appTheme: AppTheme) {
        val index = _availableThemes.value.indexOf(appTheme)
        viewModelScope.launch(Dispatchers.IO) {
            changeThemeIndexUseCase.invoke(index)
        }
    }
}
