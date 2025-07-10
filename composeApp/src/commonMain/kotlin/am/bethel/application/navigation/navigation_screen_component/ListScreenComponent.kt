package am.bethel.application.navigation.navigation_screen_component


import androidx.compose.runtime.MutableState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListScreenComponent(
    componentContext: ComponentContext,
    private val onNavigateToDetailsScreen: (Int) -> Unit
) {
    private val _songsRangeCount = MutableStateFlow(
        listOf(
            IntRange(1, 99),
            IntRange(100, 199),
            IntRange(200, 299),
            IntRange(300, 399),
            IntRange(400, 499),
            IntRange(500, 599),
            IntRange(600, 699),
            IntRange(700, 799),
            IntRange(800, 899),
            IntRange(900, 1000),
        )
    )
    val songsCount = _songsRangeCount.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    fun setSearchQuery(value: String) {
        _searchQuery.value = value
    }

    fun navigateToDetailsScreen(songIndex: Int)= onNavigateToDetailsScreen(songIndex)

}

