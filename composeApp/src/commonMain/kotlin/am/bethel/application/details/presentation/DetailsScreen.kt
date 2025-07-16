package am.bethel.application.details.presentation

import am.bethel.application.common.domain.model.getTitle
import am.bethel.application.common.presentation.components.snackbar.SnackbarState
import am.bethel.application.common.presentation.components.ui.FontBold
import am.bethel.application.common.presentation.components.ui.FontRegular
import am.bethel.application.details.presentation.popap.LoadNextSongDialog
import am.bethel.application.settings.domain.model.AppTheme
import am.bethel.application.settings.presentation.SettingsBottomSheet
import am.bethel.application.settings.presentation.SettingsViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bethelsongbookkmp.composeapp.generated.resources.Res
import bethelsongbookkmp.composeapp.generated.resources.ic_back
import bethelsongbookkmp.composeapp.generated.resources.ic_bookmark_add
import bethelsongbookkmp.composeapp.generated.resources.ic_bookmark_remove
import bethelsongbookkmp.composeapp.generated.resources.ic_load_next_song
import bethelsongbookkmp.composeapp.generated.resources.ic_settings
import bethelsongbookkmp.composeapp.generated.resources.ic_share
import bethelsongbookkmp.composeapp.generated.resources.song_number
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    currentIndex: Int,
    appTheme: AppTheme,
    viewModel: DetailsViewModel = koinInject(),
    settingsViewModel: SettingsViewModel,
    onSnackbarShown: (SnackbarState) -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    val currentSong by viewModel.currentSongs.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()
    var isLoadSongDialogVisible by rememberSaveable { mutableStateOf(false) }
    val bottomSheetExpanded = rememberSaveable { mutableStateOf(false) }
    val verticalScrollState = rememberScrollState()

    val currentFontSize by settingsViewModel.fontSize.collectAsState()
    val themes by settingsViewModel.availableThemes.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadSong(currentIndex)
        viewModel.setCurrentIndex(currentIndex)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = appTheme.backgroundColor,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = appTheme.backgroundColor
                ), title = {
                    Text(
                        text = stringResource(Res.string.song_number, currentSong?.songNumber ?: 0),
                        fontFamily = FontBold(),
                        fontStyle = FontStyle.Normal,
                        fontSize = 20.sp,
                        color = appTheme.primaryColor
                    )

                }, navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_back),
                            contentDescription = null,
                            tint = appTheme.primaryColor
                        )
                    }
                }, actions = {

                    IconButton(
                        onClick = {
                            isLoadSongDialogVisible = true
                        }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_load_next_song),
                            contentDescription = null,
                            tint = appTheme.primaryColor
                        )
                    }

                    IconButton(
                        onClick = {
//                            viewModel.shareSong(context)
                        }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_share),
                            contentDescription = null,
                            tint = appTheme.primaryColor
                        )
                    }

                    IconButton(
                        onClick = {
                            viewModel.toggleFavorite(onSnackbarShowed = onSnackbarShown)
                        }
                    ) {
                        Icon(
                            painter = if (isFavorite)
                                painterResource(Res.drawable.ic_bookmark_remove)
                            else
                                painterResource(Res.drawable.ic_bookmark_add),
                            contentDescription = null,
                            tint = appTheme.primaryColor
                        )
                    }

                    IconButton(
                        onClick = {
                            bottomSheetExpanded.value = true
                        }) {
                        Icon(
                            painterResource(Res.drawable.ic_settings),
                            contentDescription = null,
                            tint = appTheme.primaryColor
                        )
                    }
                })
        },
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(start = 24.dp, end = 4.dp)
                .verticalScroll(verticalScrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val title = currentSong?.getTitle()
            if (!title.isNullOrEmpty())
                Text(
                    text = title,
                    fontFamily = FontRegular(),
                    fontSize = 22.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = appTheme.primaryTextColor
                )

            currentSong?.getWords()?.let { words ->
                SwipeableSongText(
                    modifier = modifier,
                    words = words,
                    appTheme = appTheme,
                    fontSize = currentFontSize,
                    onNextSong = viewModel::loadNextSong,
                    onPrevSong = viewModel::loadPrevSong
                )
            }
        }
    }

    SettingsBottomSheet(
        expanded = bottomSheetExpanded,
        appTheme = appTheme,
        themes = themes,
        currentFontSize = currentFontSize,
        onFontSizeIncrease = settingsViewModel::increment,
        onFontSizeDecrease = settingsViewModel::decrement,
        onThemeChange = settingsViewModel::setUiSetting
    )

    if (isLoadSongDialogVisible) {
        LoadNextSongDialog(
            theme = appTheme,
            onDismiss = { isLoadSongDialogVisible = false },
            onConfirm = {
                viewModel.loadSongByIndex(
                    index = it,
                    onSnackbarShowed = onSnackbarShown,
                    onLoadComplete = {
                        isLoadSongDialogVisible = false
                    }
                )
            }
        )
    }
}