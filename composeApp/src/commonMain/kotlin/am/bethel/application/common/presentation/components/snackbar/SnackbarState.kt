package am.bethel.application.common.presentation.components.snackbar

import bethelsongbookkmp.composeapp.generated.resources.Res
import bethelsongbookkmp.composeapp.generated.resources.empty_string
import bethelsongbookkmp.composeapp.generated.resources.ic_error
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource


sealed class SnackbarState(val message: StringResource, val icon: DrawableResource? = null) {
    data class Error(
        val _message: StringResource = Res.string.empty_string,
        val _icon: DrawableResource = Res.drawable.ic_error,
    ) : SnackbarState(message = _message, icon = _icon)

    data class Success(
        val _message: StringResource  = Res.string.empty_string,
        val _icon: DrawableResource ? = null,
    ) : SnackbarState(message = _message, icon = _icon)
}