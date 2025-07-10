package am.bethel.application.navigation.bottom_navigation

import am.bethel.application.navigation.navigation_component.RootComponent
import bethelsongbookkmp.composeapp.generated.resources.Res
import bethelsongbookkmp.composeapp.generated.resources.compose_multiplatform
import bethelsongbookkmp.composeapp.generated.resources.ic_bookmark
import bethelsongbookkmp.composeapp.generated.resources.ic_list
import bethelsongbookkmp.composeapp.generated.resources.ic_search
import org.jetbrains.compose.resources.DrawableResource

sealed class BottomNavItem(
    val label: String,
    val icon: DrawableResource,
    val config: RootComponent.Configuration
) {
    object List : BottomNavItem(
        label = "Ցանկ",
        icon = Res.drawable.ic_list,
        config = RootComponent.Configuration.List,
    )
    object Bookmarked : BottomNavItem(
        label = "Էջանշված",
        icon = Res.drawable.ic_bookmark,
        config = RootComponent.Configuration.Bookmarked
    )
    object Search : BottomNavItem(
        label = "Որոնում",
        icon = Res.drawable.ic_search,
        config = RootComponent.Configuration.Search
    )

    companion object {
        val items = listOf(List, Bookmarked, Search)
    }
}
