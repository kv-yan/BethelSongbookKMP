package am.bethel.application.details.presentation

import am.bethel.application.bookmarked.domain.usecase.AddToFavoritesUseCase
import am.bethel.application.bookmarked.domain.usecase.IsFavoriteUseCase
import am.bethel.application.bookmarked.domain.usecase.RemoveFromFavoritesUseCase
import am.bethel.application.common.domain.model.Song
import am.bethel.application.common.presentation.components.snackbar.SnackbarState
import am.bethel.application.details.domain.usecase.GetSongByIndexUseCase
import bethelsongbookkmp.composeapp.generated.resources.Res
import bethelsongbookkmp.composeapp.generated.resources.ic_bookmark_added
import bethelsongbookkmp.composeapp.generated.resources.ic_bookmark_remove
import bethelsongbookkmp.composeapp.generated.resources.ic_error
import bethelsongbookkmp.composeapp.generated.resources.no_song_found_by_number
import bethelsongbookkmp.composeapp.generated.resources.song_marked
import bethelsongbookkmp.composeapp.generated.resources.song_unmarked
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class DetailsViewModel(
    private val getSongByIndexUseCase: GetSongByIndexUseCase,
    private val addToFavoritesUseCaseImpl: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
) : KoinComponent {

    companion object {
        private const val MIN_INDEX = 1
        private const val MAX_INDEX = 1000
    }

    val coroutineScope = CoroutineScope(Dispatchers.IO)

    private var favoriteJob: Job? = null

    private val _currentSong = MutableStateFlow<Song?>(Song())
    val currentSongs = _currentSong.asStateFlow()

    private val _currentIndex = MutableStateFlow(0)

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    fun setCurrentIndex(index: Int) {
        _currentIndex.value = index
    }

    private fun getSongByIndex(index: Int) {
        getSongByIndexUseCase(index).onEach {
            _currentSong.value = it
            it?.let { song -> observeFavoriteState(song) }
        }.launchIn(coroutineScope)
    }

    fun loadSong(index: Int) {
        getSongByIndexUseCase(index).onEach { song ->
            _currentSong.value = song
        }.catch {
            println("error loading song :: $it")
        }.launchIn(scope = coroutineScope)
    }

    fun loadNextSong() {
        if (_currentIndex.value < MAX_INDEX) {
            _currentIndex.value++
            getSongByIndex(_currentIndex.value)
        }
    }

    fun toggleFavorite(onSnackbarShowed: (SnackbarState) -> Unit) {
        val song = _currentSong.value ?: return

        coroutineScope.launch {
            if (_isFavorite.value) {
                // update button state first
                _isFavorite.value = false
                removeFromFavoritesUseCase(song)
                onSnackbarShowed(
                    SnackbarState.Success(
                        _message = Res.string.song_unmarked,
                        _icon = Res.drawable.ic_bookmark_remove
                    )
                )
            } else {
                _isFavorite.value = true
                addToFavoritesUseCaseImpl(song)
                onSnackbarShowed(
                    SnackbarState.Success(
                        _message = Res.string.song_marked,
                        _icon = Res.drawable.ic_bookmark_added
                    )
                )
            }
        }
    }

    fun loadPrevSong() {
        if (_currentIndex.value > MIN_INDEX) {
            _currentIndex.value--
            getSongByIndex(_currentIndex.value)
        }
    }

    private fun observeFavoriteState(song: Song) {
        favoriteJob?.cancel()
        favoriteJob = isFavoriteUseCase(song).onEach {
            _isFavorite.value = it
        }.launchIn(coroutineScope)
    }

    fun loadSongByIndex(
        index: Int,
        onSnackbarShowed: (SnackbarState) -> Unit,
        onLoadComplete: () -> Unit = {}
    ) {
        _currentIndex.value = index
        if (index < MIN_INDEX || index > MAX_INDEX) {
            onSnackbarShowed(
                SnackbarState.Error(
                    _message = Res.string.no_song_found_by_number,
                    _icon = Res.drawable.ic_error
                )
            )
        } else {
            getSongByIndex(index)
            onLoadComplete()
        }
    }
}