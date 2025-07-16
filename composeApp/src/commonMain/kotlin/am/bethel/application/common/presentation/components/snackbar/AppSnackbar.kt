package am.bethel.application.common.presentation.components.snackbar


import am.betel.songbook.common.presentation.ui.theme.Shape16
import am.bethel.application.common.presentation.components.ui.FontRegular
import am.bethel.application.common.presentation.components.ui.RoseRed
import am.bethel.application.settings.domain.model.AppTheme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppSnackbar(
    modifier: Modifier = Modifier,
    theme: AppTheme,
    state: SnackbarState,
    onDismiss: (SnackbarState) -> Unit = {},
) {
    var isShown by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(state) {
        isShown = true
        delay(1500)
        isShown = false
        delay(200)
        onDismiss(state)
    }

    AnimatedVisibility(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        visible = isShown,
        enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
    ) {
        val (backgroundColor, textColor) = when (state) {
            is SnackbarState.Error -> Pair(RoseRed, Color.White)
            is SnackbarState.Success -> Pair(theme.primaryColor, theme.backgroundColor)
        }

        Card(
            modifier = Modifier.padding(horizontal = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = Shape16,
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor,
                contentColor = textColor
            )
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                state.icon?.let {
                    Icon(
                        painter = painterResource(it),
                        contentDescription = null,
                        tint = textColor
                    )
                }

                Text(
                    text = stringResource(state.message),
                    modifier = Modifier.weight(1f),
                    fontFamily = FontRegular(),
                    color = textColor,
                    fontSize = 16.sp
                )
            }
        }
    }
}