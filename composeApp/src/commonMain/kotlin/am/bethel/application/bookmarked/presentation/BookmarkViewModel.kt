package am.bethel.application.bookmarked.presentation

import am.bethel.application.bookmarked.domain.usecase.GetFavoriteSongsUseCase
import am.bethel.application.bookmarked.domain.usecase.RemoveFromFavoritesUseCase
import am.bethel.application.common.domain.model.Song
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

class BookmarkViewModel(
    getFavoriteSongsUseCase: GetFavoriteSongsUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
) : KoinComponent {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val _favoriteSongs = MutableStateFlow<List<Song>>(emptyList())
    val favoriteSongs: StateFlow<List<Song>> = _favoriteSongs.asStateFlow()

    init {
        getFavoriteSongsUseCase().onEach {
            _favoriteSongs.value = it
            println("favorite songs :: $it")
        }.launchIn(coroutineScope)
    }

    fun removeFavoriteSong(song: Song, /*showSnackbar: (SnackbarState) -> Unit*/) {
        coroutineScope.launch(Dispatchers.IO) {
            removeFromFavoritesUseCase(song)
/*
            showSnackbar(
                SnackbarState.Success(
                    _message = R.string.song_unmarked,
                    _icon = R.drawable.ic_bookmark_remove
                )
            )
*/
        }
    }
}