package am.bethel.songbook

import am.bethel.application.bookmarked.presentation.BookmarkedScreen
import am.bethel.application.details.presentation.DetailsScreen
import am.bethel.application.list.presentation.ListScreen
import am.bethel.application.navigation.bottom_navigation.AppBottomNavigation
import am.bethel.application.navigation.bottom_navigation.BottomNavItem
import am.bethel.application.navigation.navigation_component.RootComponent
import am.bethel.application.search.presentation.SearchScreen
import am.bethel.application.settings.domain.model.AppTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.pushNew

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun App(
    root: RootComponent
) {
    val childStack by root.childStack.subscribeAsState()
    val currentChild = childStack.active.instance
    val appTheme = AppTheme.LightBlue700

    // Layout with bottom bar
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            Children(
                stack = childStack,
                animation = stackAnimation(slide())
            ) { child ->
                when (val component = child.instance) {
                    is RootComponent.Child.Bookmarked -> BookmarkedScreen(
                        appTheme = appTheme,
                        component = component.component,
                        navigateToDetails = {
                            root.navigateTo(RootComponent.Configuration.Details(it.toString()))
                        },
                        onBackClick = {
                            root.navigateBack()
                        }
                    )
                    is RootComponent.Child.Details -> DetailsScreen(
                        appTheme = appTheme,
                        currentIndex = component.component.songIndex.collectAsState().value,
                        detailsComponent = component.component,
                        onBackClick = {
                            root.navigateBack()
                        }
                    )
                    is RootComponent.Child.List -> ListScreen(
                        appTheme = appTheme,
                        listComponent = component.component,
                        navigateToDetails = {
                            root.navigateTo(RootComponent.Configuration.Details(it.toString()))
                        }
                    )
                    is RootComponent.Child.Search -> SearchScreen(
                        appTheme = appTheme,
                        component = component.component,
                        navigateToDetails = {
                            root.navigateTo(RootComponent.Configuration.Details(it.toString()))
                        },
                        onBackClick = {
                            root.navigateBack()
                        }
                    )
                }
            }
        }

        // Show BottomNavigation only if not Details
        if (currentChild !is RootComponent.Child.Details) {
            AppBottomNavigation(
                currentChild = currentChild,
                appTheme = appTheme,
                onNavigateTo = {
                    root.navigateTo(it)
                }
            )
        }
    }
}
