package am.bethel.application.search.presentation

import am.bethel.application.common.domain.model.Song
import am.bethel.application.search.domain.usecase.SearchSongByTextUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent

class SearchViewModel(
    val searchSongByTextUseCase: SearchSongByTextUseCase,
) : KoinComponent {

    val scope = CoroutineScope(Dispatchers.IO)
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _foundedSongs = MutableStateFlow<List<Song>>(emptyList())
    val foundedSongs = _foundedSongs.asStateFlow()

    private val _nothingFounded = MutableStateFlow(false)
    val nothingFounded = _nothingFounded.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        _nothingFounded.value = false
    }

    fun onSearchClick() {
        _isLoading.value = true
        _nothingFounded.value = false
        searchSongByTextUseCase(_searchQuery.value).onEach {
            _foundedSongs.value = it
            _nothingFounded.value = it.isEmpty()
            _isLoading.value = false
        }.catch {
            _foundedSongs.value = emptyList()
            _nothingFounded.value = true
            _isLoading.value = false
        }.launchIn(scope = scope)
    }
}