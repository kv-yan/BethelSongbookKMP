package am.bethel.application.details.presentation

import am.bethel.application.common.presentation.components.ui.FontRegular
import am.bethel.application.settings.domain.model.AppTheme
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SwipeableSongText(
    modifier: Modifier = Modifier,
    words: String,
    appTheme: AppTheme,
    fontSize: Float = 16f,
    onNextSong: () -> Unit,
    onPrevSong: () -> Unit,
) {
    var totalDrag by remember { mutableFloatStateOf(0f) }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        when {
                            totalDrag > 100 -> onPrevSong()
                            totalDrag < -100 -> onNextSong()
                        }
                        totalDrag = 0f
                    },
                    onHorizontalDrag = { change, dragAmount ->
                        change.consume()
                        totalDrag += dragAmount
                    }
                )
            }
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 40.dp
            )
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .heightIn(min = 560.dp, max = 1500.dp)
        ) {
            Text(
                text = words,
                fontFamily = FontRegular(),
                fontStyle = FontStyle.Normal,
                fontSize = fontSize.sp,
                color = appTheme.primaryTextColor
            )
        }
    }
}
