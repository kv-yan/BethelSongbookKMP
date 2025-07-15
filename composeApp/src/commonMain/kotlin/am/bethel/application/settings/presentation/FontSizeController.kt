package am.bethel.application.settings.presentation

import am.betel.songbook.common.presentation.ui.theme.Shape10
import am.bethel.application.settings.domain.model.AppTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import bethelsongbookkmp.composeapp.generated.resources.Res
import bethelsongbookkmp.composeapp.generated.resources.font_size_controller_text
import org.jetbrains.compose.resources.stringResource

@Composable
fun FontSizeController(
    modifier: Modifier = Modifier,
    appTheme: AppTheme,
    fontSize: TextUnit,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = appTheme.primaryColor,
            contentColor = appTheme.backgroundColor
        ),
        shape = Shape10
    ) {
        Text(
            text = stringResource(Res.string.font_size_controller_text),
            fontSize = fontSize
        )
    }
}
