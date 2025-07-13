package am.bethel.application.details.presentation

import am.bethel.application.common.domain.model.Song
import am.bethel.application.details.domain.usecase.GetSongByIndexUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent

class DetailsViewModel(
    private val getSongByIndexUseCase: GetSongByIndexUseCase
) : KoinComponent {
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

    fun loadSong(index: Int) {
        getSongByIndexUseCase(index).onEach { song ->
            _currentSong.value = song
            println("song :: $song")
        }.catch {
            println("error loading song :: $it")
        }.launchIn(scope = coroutineScope)
    }

}