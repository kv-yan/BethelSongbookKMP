package am.bethel.songbook

import am.bethel.application.bookmarked.presentation.BookmarkedScreen
import am.bethel.application.common.presentation.components.snackbar.AppSnackbar
import am.bethel.application.common.presentation.components.snackbar.SnackbarState
import am.bethel.application.common.presentation.components.ui.PurpleGrey40
import am.bethel.application.details.presentation.DetailsScreen
import am.bethel.application.list.presentation.ListScreen
import am.bethel.application.navigation.bottom_navigation.AppBottomNavigation
import am.bethel.application.navigation.bottom_navigation.BottomNavItem
import am.bethel.application.navigation.navigation_component.RootComponent
import am.bethel.application.search.presentation.SearchScreen
import am.bethel.application.settings.presentation.SettingsViewModel
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import bethelsongbookkmp.composeapp.generated.resources.Res
import bethelsongbookkmp.composeapp.generated.resources.ic_app_logo
import bethelsongbookkmp.composeapp.generated.resources.no_song_found_by_number
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.Direction
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimator
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

private fun RootComponent.Configuration.navIndex(): Int =
    BottomNavItem.items.indexOfFirst { it.config == this }
        .let { if (it < 0) Int.MAX_VALUE else it }

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun App(
    root: RootComponent,
    settingsViewModel: SettingsViewModel = koinInject(),
    onSystemBarColorChange: (isDarkIcons: Boolean) -> Unit = {},
    keepScreenOn: (Boolean) -> Unit = {}
) {
    val childStack by root.childStack.subscribeAsState()
    val currentChild = childStack.active.instance
    val currentConfig = childStack.active.configuration
    var isLoadingSongs by remember { mutableStateOf(false) }
    val snackBars = remember { mutableStateListOf<SnackbarState>() }
    val appTheme by settingsViewModel.uiSettings.collectAsState()
    val onSnackbarShown: (SnackbarState) -> Unit = { snackBars.add(it) }
    var isShowingSplash by remember { mutableStateOf(false) }
    val isScreenKeepAwoken by settingsViewModel.isScreenKeepAwake.collectAsState()

    // Derive slide direction from the actual stack change — no manual flag needed.
    // Details has navIndex = Int.MAX_VALUE so navigating to it is always forward.
    val prevConfigRef = remember { arrayOf(currentConfig) }
    val isForwardNavigation = remember { arrayOf(true) }
    if (currentConfig != prevConfigRef[0]) {
        isForwardNavigation[0] = currentConfig.navIndex() >= prevConfigRef[0].navIndex()
        prevConfigRef[0] = currentConfig
    }

    val imageSize by animateDpAsState(
        targetValue = if (isShowingSplash) 250.dp else 120.dp,
        animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing)
    )

    val alpha by animateFloatAsState(
        targetValue = if (isShowingSplash) 1f else 0f,
        animationSpec = tween(durationMillis = 700)
    )

    LaunchedEffect(Unit) {
        delay(700)
        isShowingSplash = false
    }

    LaunchedEffect(isScreenKeepAwoken) {
        println("effect worked $isScreenKeepAwoken")
        keepScreenOn(isScreenKeepAwoken)
    }

    appTheme?.let { theme ->

        LaunchedEffect(theme) {
            onSystemBarColorChange(theme.darkIcons)
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = if (isShowingSplash) Color.White else theme.backgroundColor,
        ) {
            if (isShowingSplash)
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_app_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(imageSize)
                            .graphicsLayer(alpha = alpha)
                            .padding(bottom = 16.dp)
                    )
                }
            else
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
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
                                    animation = predictiveBackAnimation(
                                        backHandler = root.backHandler,
                                        fallbackAnimation = stackAnimation(
                                            stackAnimator(animationSpec = tween(300)) { factor, direction, content ->
                                                BoxWithConstraints {
                                                    val widthPx = with(LocalDensity.current) { maxWidth.toPx() }
                                                    val adjustedFactor = when (direction) {
                                                        // ENTER_FRONT and EXIT_BACK use the same sign:
                                                        // forward → factor as-is, backward → negated
                                                        Direction.ENTER_FRONT,
                                                        Direction.EXIT_BACK ->
                                                            if (isForwardNavigation[0]) factor else -factor
                                                        // Pop (back gesture / back button) — always standard
                                                        else -> factor
                                                    }
                                                    content(
                                                        Modifier.offset {
                                                            IntOffset(x = (adjustedFactor * widthPx).toInt(), y = 0)
                                                        }
                                                    )
                                                }
                                            }
                                        ),
                                        onBack = { root.navigateBack() }
                                    )
                                ) { child ->
                                    when (val component = child.instance) {
                                        is RootComponent.Child.Bookmarked ->
                                            BookmarkedScreen(
                                                modifier = Modifier.fillMaxSize(),
                                                appTheme = theme,
                                                onBackClick = { root.navigateBack() },
                                                onSnackbarShown = onSnackbarShown,
                                                navigateToDetails = {
                                                    root.navigateTo(
                                                        RootComponent.Configuration.Details(it.toString())
                                                    )
                                                }
                                            )

                                        is RootComponent.Child.Details -> DetailsScreen(
                                            appTheme = theme,
                                            currentIndex = component.component.songIndex.collectAsState().value,
                                            settingsViewModel = settingsViewModel,
                                            onSnackbarShown = onSnackbarShown,
                                            onBackClick = { root.navigateBack() }
                                        )

                                        is RootComponent.Child.List -> ListScreen(
                                            appTheme = theme,
                                            navigateToDetails = {
                                                if (it >= 1 && it <= 1000)
                                                    root.navigateTo(
                                                        RootComponent.Configuration.Details(it.toString())
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
                                            onBackClick = { root.navigateBack() }
                                        )
                                    }
                                }
                            }
                        }

                        if (currentChild !is RootComponent.Child.Details) {
                            AppBottomNavigation(
                                currentChild = currentChild,
                                appTheme = theme,
                                onNavigateTo = { root.navigateTo(it) }
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