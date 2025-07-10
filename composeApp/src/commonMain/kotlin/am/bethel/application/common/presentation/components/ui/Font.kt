package am.bethel.application.common.presentation.components.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import bethelsongbookkmp.composeapp.generated.resources.Res
import bethelsongbookkmp.composeapp.generated.resources.grapalat_blit
import bethelsongbookkmp.composeapp.generated.resources.grapalat_bold
import bethelsongbookkmp.composeapp.generated.resources.grapalat_reg
import bethelsongbookkmp.composeapp.generated.resources.grapalat_rit
import org.jetbrains.compose.resources.Font


@Composable
fun FontRegular() = FontFamily(
    Font(
        resource = Res.font.grapalat_reg,
    )
)


@Composable
fun FontBold() = FontFamily(
    Font(
        resource = Res.font.grapalat_bold,
    )
)


@Composable
fun FontBoldItalic() = FontFamily(
    Font(
        resource = Res.font.grapalat_blit,
    )
)


@Composable
fun FontItalic() = FontFamily(
    Font(
        resource = Res.font.grapalat_rit,
    )
)

