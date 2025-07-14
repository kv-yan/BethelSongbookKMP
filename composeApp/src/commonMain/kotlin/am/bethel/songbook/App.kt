package am.bethel.songbook

import am.bethel.application.bookmarked.presentation.BookmarkedScreen
import am.bethel.application.common.presentation.components.ui.PurpleGrey40
import am.bethel.application.details.presentation.DetailsScreen
import am.bethel.application.list.presentation.ListScreen
import am.bethel.application.navigation.bottom_navigation.AppBottomNavigation
import am.bethel.application.navigation.navigation_component.RootComponent
import am.bethel.application.search.presentation.SearchScreen
import am.bethel.application.settings.domain.model.AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun App(
    root: RootComponent
) {
    val childStack by root.childStack.subscribeAsState()
    val currentChild = childStack.active.instance
    val appTheme = AppTheme.LightBlue700
    var isLoadingSongs by remember { mutableStateOf(false) }

    // Layout with bottom bar
    Column(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.weight(1f)) {
            if (isLoadingSongs) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .background(PurpleGrey40.copy(0.2f)),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Children(
                    stack = childStack,
                    animation = stackAnimation(slide())
                ) { child ->
                    when (val component = child.instance) {
                        is RootComponent.Child.Bookmarked -> BookmarkedScreen(
                            appTheme = appTheme,
                            onBackClick = { root.navigateBack() },
                            navigateToDetails = {
                                root.navigateTo(RootComponent.Configuration.Details(it.toString()))
                            }
                        )

                        is RootComponent.Child.Details -> DetailsScreen(
                            appTheme = appTheme,
                            currentIndex = component.component.songIndex.collectAsState().value,
                            onBackClick = {
                                root.navigateBack()
                            }
                        )

                        is RootComponent.Child.List -> ListScreen(
                            appTheme = appTheme,
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
        }

        // Show BottomNavigation only if not Details
        if (currentChild !is RootComponent.Child.Details) {
            AppBottomNavigation(
                currentChild = currentChild, appTheme = appTheme, onNavigateTo = {
                    root.navigateTo(it)
                })
        }
    }
}
