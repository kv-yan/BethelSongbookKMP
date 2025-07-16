package am.bethel.songbook

import am.bethel.application.bookmarked.presentation.BookmarkedScreen
import am.bethel.application.common.presentation.components.snackbar.AppSnackbar
import am.bethel.application.common.presentation.components.snackbar.SnackbarState
import am.bethel.application.common.presentation.components.ui.PurpleGrey40
import am.bethel.application.details.presentation.DetailsScreen
import am.bethel.application.list.presentation.ListScreen
import am.bethel.application.navigation.bottom_navigation.AppBottomNavigation
import am.bethel.application.navigation.navigation_component.RootComponent
import am.bethel.application.search.presentation.SearchScreen
import am.bethel.application.settings.presentation.SettingsViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import bethelsongbookkmp.composeapp.generated.resources.Res
import bethelsongbookkmp.composeapp.generated.resources.no_song_found_by_number
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import org.koin.compose.koinInject

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun App(
    root: RootComponent,
    settingsViewModel: SettingsViewModel = koinInject(),
    onSystemBarColorChange: (isDarkIcons: Boolean) -> Unit = {}
) {
    val childStack by root.childStack.subscribeAsState()
    val currentChild = childStack.active.instance
    var isLoadingSongs by remember { mutableStateOf(false) }
    val snackBars = remember { mutableStateListOf<SnackbarState>() }
    val appTheme by settingsViewModel.uiSettings.collectAsState()

    val onSnackbarShown: (SnackbarState) -> Unit = { snackBars.add(it) }
    appTheme?.let { theme ->

        LaunchedEffect(theme) {
            onSystemBarColorChange(theme.darkIcons)
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = theme.backgroundColor,
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .padding(bottom = it.calculateBottomPadding())
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (isLoadingSongs) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(1f)
                                    .background(PurpleGrey40.copy(0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        } else {
                            Children(
                                stack = childStack,
                                animation = stackAnimation(slide())
                            ) { child ->
                                when (val component = child.instance) {
                                    is RootComponent.Child.Bookmarked ->
                                        BookmarkedScreen(
                                            appTheme = theme,
                                            onBackClick = { root.navigateBack() },
                                            onSnackbarShown = onSnackbarShown,
                                            navigateToDetails = {
                                                root.navigateTo(
                                                    RootComponent.Configuration.Details(
                                                        it.toString()
                                                    )
                                                )
                                            }
                                        )

                                    is RootComponent.Child.Details -> DetailsScreen(
                                        appTheme = theme,
                                        currentIndex = component.component.songIndex.collectAsState().value,
                                        settingsViewModel = settingsViewModel,
                                        onSnackbarShown = onSnackbarShown,
                                        onBackClick = {
                                            root.navigateBack()
                                        }
                                    )

                                    is RootComponent.Child.List -> ListScreen(
                                        appTheme = theme,
                                        navigateToDetails = {
                                            if (it >= 0 && it <= 1000)
                                                root.navigateTo(
                                                    RootComponent.Configuration.Details(
                                                        it.toString()
                                                    )
                                                )
                                            else
                                                onSnackbarShown(
                                                    SnackbarState.Error(
                                                        _message = Res.string.no_song_found_by_number
                                                    )
                                                )
                                        }
                                    )

                                    is RootComponent.Child.Search -> SearchScreen(
                                        appTheme = theme,
                                        navigateToDetails = {
                                            root.navigateTo(
                                                RootComponent.Configuration.Details(it.toString())
                                            )
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
                            currentChild = currentChild,
                            appTheme = theme,
                            onNavigateTo = {
                                root.navigateTo(it)
                            }
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 32.dp)
                ) {
                    snackBars.forEachIndexed { index, state ->
                        AppSnackbar(
                            modifier = Modifier
                                .offset(y = (index * 8).dp)
                                .zIndex(index.toFloat()),
                            theme = theme,
                            state = state
                        ) {
                            snackBars.remove(state)
                        }
                    }
                }
            }
        }
    }
}
