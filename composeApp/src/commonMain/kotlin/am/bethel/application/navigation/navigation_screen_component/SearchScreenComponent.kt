package am.bethel.application.navigation.navigation_screen_component


import am.bethel.application.common.domain.model.Song
import androidx.compose.runtime.MutableState
import androidx.lifecycle.viewModelScope
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchScreenComponent(
    componentContext: ComponentContext,
) {
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
        // TODO: have an full functionality
/*
        searchSongByTextUseCase(_searchQuery.value).onEach {
            _foundedSongs.value = it
            _nothingFounded.value = it.isEmpty()
            _isLoading.value = false
        }.catch {
            _foundedSongs.value = emptyList()
            _nothingFounded.value = true
            _isLoading.value = false
        }.launchIn(viewModelScope)
*/
    }
}
