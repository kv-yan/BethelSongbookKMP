package am.bethel.application.bookmarked.presentation

import am.bethel.application.common.domain.model.Song
import am.bethel.application.common.presentation.components.ui.FontRegular
import am.bethel.application.settings.domain.model.AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bethelsongbookkmp.composeapp.generated.resources.Res
import bethelsongbookkmp.composeapp.generated.resources.ic_bookmark_added
import bethelsongbookkmp.composeapp.generated.resources.ic_delete
import bethelsongbookkmp.composeapp.generated.resources.song_number
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BookmarkedSongItem(
    modifier: Modifier = Modifier,
    song: Song,
    appTheme: AppTheme,
    onClick: (Int) -> Unit = {},
    onRemoveClick: (Int) -> Unit = {},
) {

    Row(modifier = modifier.clickable { onClick(song.songNumber.toInt()) }) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(appTheme.primaryColor, shape = CircleShape)
                .padding(8.dp),
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                painter = painterResource(Res.drawable.ic_bookmark_added),
                contentDescription = null,
                tint = appTheme.backgroundColor
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {

            Text(
                modifier = Modifier,
                text = stringResource(Res.string.song_number, song.songNumber),
                fontSize = 17.sp,
                color = appTheme.primaryTextColor
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = song.getWords(),
                fontFamily = FontRegular(),
                maxLines = 2,
                fontSize = 15.sp,
                overflow = TextOverflow.Ellipsis,
                color = appTheme.primaryTextColor
            )
        }

        IconButton(
            onClick = { onRemoveClick(song.id) }
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_delete),
                contentDescription = null,
                tint = appTheme.primaryColor
            )
        }
    }
}