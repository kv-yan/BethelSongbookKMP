package am.bethel.application.navigation.navigation_screen_component


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailsScreenComponent(
    index: Int
) {
    private val _songIndex: MutableStateFlow<Int> = MutableStateFlow(index)
    val songIndex = _songIndex.asStateFlow()

}
