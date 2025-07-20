package am.bethel.application.details.presentation.popap

import am.betel.songbook.common.presentation.ui.theme.Shape10
import am.bethel.application.common.presentation.components.inputFeald.AppInputField
import am.bethel.application.common.presentation.components.ui.FontRegular
import am.bethel.application.settings.domain.model.AppTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import bethelsongbookkmp.composeapp.generated.resources.Res
import bethelsongbookkmp.composeapp.generated.resources.enter_next_song_index
import bethelsongbookkmp.composeapp.generated.resources.example_index
import bethelsongbookkmp.composeapp.generated.resources.search
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoadNextSongDialog(
    modifier: Modifier = Modifier,
    theme: AppTheme,
    onDismiss: () -> Unit = {},
    onConfirm: (Int) -> Unit = {},
) {
    var input by remember { mutableStateOf("") }

    AlertDialog(
        modifier = modifier,
        shape = Shape10,
        onDismissRequest = { onDismiss() },
        containerColor = theme.backgroundColor,
        titleContentColor = theme.primaryColor,
        title = {
            Text(
                text = stringResource(Res.string.enter_next_song_index),
                fontFamily = FontRegular(),
                fontSize = 18.sp,
            )
        },
        text = {
            AppInputField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.example_index),
                searchQuery = input,
                appTheme = theme,
                onValueChange = { input = it },
                trailingIcon = {
                    TextButton(
                        enabled = input.isNotEmpty(),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = if (input.isNotEmpty()) theme.primaryColor else theme.unfocusedColor,
                            disabledContentColor = theme.unfocusedColor
                        ),
                        onClick = { onConfirm(input.toInt()) }) {
                        Text(text = stringResource(Res.string.search))
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Number,
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onConfirm(input.toInt())
                    }
                )
            )
        },
        confirmButton = {},
    )
}
