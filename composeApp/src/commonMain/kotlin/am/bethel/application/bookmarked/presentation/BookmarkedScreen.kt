package am.bethel.application.bookmarked.presentation

import am.bethel.application.common.presentation.components.ui.FontRegular
import am.bethel.application.navigation.navigation_screen_component.BookmarkedScreenComponent
import am.bethel.application.settings.domain.model.AppTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bethelsongbookkmp.composeapp.generated.resources.Res
import bethelsongbookkmp.composeapp.generated.resources.bookmarked_songs
import bethelsongbookkmp.composeapp.generated.resources.ic_back
import bethelsongbookkmp.composeapp.generated.resources.no_bookmarked_songs
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkedScreen(
    modifier: Modifier = Modifier,
    appTheme: AppTheme,
    component: BookmarkedScreenComponent,
    navigateToDetails: (Int) -> Unit,
    onSnackbarShown: () -> Unit = {},
    onBackClick: () -> Unit = {}
){

    val songs by component.favoriteSongs.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = appTheme.backgroundColor,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = appTheme.backgroundColor
                ), navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_back),
                            contentDescription = null,
                            tint = appTheme.primaryColor
                        )
                    }
                }, title = {
                    Text(
                        text = stringResource(Res.string.bookmarked_songs),
                        fontFamily = FontRegular(),
                        color = appTheme.primaryColor
                    )
                })
        }
    ) { innerPadding ->
        if (songs.isEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                text = stringResource(Res.string.no_bookmarked_songs),
                fontFamily = FontRegular(),
                color = appTheme.primaryColor,
                fontSize = 24.sp,
                textAlign = TextAlign.Center

            )

        } else LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(bottom = 64.dp),
        ) {
            items(
                items = songs,
                key = { it.id }
            ) { song ->
                BookmarkedSongItem(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    song = song,
                    appTheme = appTheme,
                    onClick = { navigateToDetails(it) },
                    onRemoveClick = {
                        component.removeFavoriteSong(
                            song = song,
                            showSnackbar = onSnackbarShown
                        )
                    }
                )
            }
        }
    }
}