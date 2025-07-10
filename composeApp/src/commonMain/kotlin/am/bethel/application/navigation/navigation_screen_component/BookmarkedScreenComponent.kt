package am.bethel.application.navigation.navigation_screen_component


import am.bethel.application.common.domain.model.Song
import androidx.compose.runtime.MutableState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookmarkedScreenComponent(
    componentContext: ComponentContext,
) {
    private val _favoriteSongs: MutableStateFlow<List<Song>> = MutableStateFlow(emptyList())
    val favoriteSongs = _favoriteSongs.asStateFlow()

    fun removeFavoriteSong(song: Song, showSnackbar: () -> Unit) {
        _favoriteSongs.value = _favoriteSongs.value - song
        // TODO: relly remove from db
        showSnackbar()
    }
}
