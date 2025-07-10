package am.bethel.application.settings.domain.model

import am.bethel.application.common.presentation.components.ui.Blue700
import am.bethel.application.common.presentation.components.ui.Pink40
import am.bethel.application.common.presentation.components.ui.Purple40
import am.bethel.application.common.presentation.components.ui.RoseRed
import am.bethel.application.common.presentation.components.ui.TestBue
import am.bethel.application.common.presentation.components.ui.TestCoral
import am.bethel.application.common.presentation.components.ui.TestGreen
import am.bethel.application.common.presentation.components.ui.gray_backgrount1
import am.bethel.application.common.presentation.components.ui.gray_backgrount2
import androidx.compose.ui.graphics.Color

enum class AppTheme(
    val backgroundColor: Color,
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val unfocusedColor: Color,
    val primaryColor: Color,
    val darkIcons: Boolean,
) {
    LightBlue700(
        backgroundColor = Color.White,
        primaryTextColor = Color.Black,
        secondaryTextColor = Color.Gray,
        unfocusedColor = Color.LightGray,
        primaryColor = Blue700,
        darkIcons = true
    ),

    LightRed(
        backgroundColor = Color.White,
        primaryTextColor = Color.Black,
        secondaryTextColor = Color.Gray,
        unfocusedColor = Color.LightGray,
        primaryColor = RoseRed,
        darkIcons = true
    ),

    LightPurple40(
        backgroundColor = Color.White,
        primaryTextColor = Color.Black,
        secondaryTextColor = Color.Gray,
        unfocusedColor = Color.LightGray,
        primaryColor = Purple40,
        darkIcons = true
    ),

    LightPink40(
        backgroundColor = Color.White,
        primaryTextColor = Color.Black,
        secondaryTextColor = Color.Gray,
        unfocusedColor = Color.LightGray,
        primaryColor = Pink40,
        darkIcons = true
    ),

    LightCoral(
        backgroundColor = Color.White,
        primaryTextColor = Color.Black,
        secondaryTextColor = Color.Gray,
        unfocusedColor = Color.LightGray,
        primaryColor = TestCoral,
        darkIcons = true
    ),

    DarkLightGray(
        backgroundColor = gray_backgrount1,
        primaryTextColor = Color.White,
        secondaryTextColor = Color.White,
        unfocusedColor = Color.LightGray,
        primaryColor = TestBue,
        darkIcons = false
    ),
    DarkDarkGrayBlue(
        backgroundColor = gray_backgrount2,
        primaryTextColor = Color.White,
        secondaryTextColor = Color.White,
        unfocusedColor = Color.LightGray,
        primaryColor = TestBue,
        darkIcons = false
    ),
    DarkDarkGrayGreen(
        backgroundColor = gray_backgrount2,
        primaryTextColor = Color.White,
        secondaryTextColor = Color.White,
        unfocusedColor = Color.LightGray,
        primaryColor = TestGreen,
        darkIcons = false
    ),

}