package am.bethel.application.navigation.navigation_screen_component


import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailsScreenComponent(
    componentContext: ComponentContext,
    index: Int
) {
    private val _songIndex: MutableStateFlow<Int> = MutableStateFlow(index)
    val songIndex = _songIndex.asStateFlow()

    fun setSongIndex(index: Int) {
        _songIndex.value = index
    }
}
