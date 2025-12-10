package am.bethel.application.settings.domain.model

import am.bethel.application.common.presentation.components.ui.TestBlue
import am.bethel.application.common.presentation.components.ui.TestGreen
import am.bethel.application.common.presentation.components.ui.TestOrange
import am.bethel.application.common.presentation.components.ui.GrayBackground2
import androidx.compose.ui.graphics.Color

enum class AppTheme(
    val backgroundColor: Color,
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val unfocusedColor: Color,
    val primaryColor: Color,
    val darkIcons: Boolean,
) {
    LightOrange(
        backgroundColor = Color.White,
        primaryTextColor = Color.Black,
        secondaryTextColor = Color.Gray,
        unfocusedColor = Color.LightGray,
        primaryColor = TestOrange,
        darkIcons = true
    ),
    DarkDarkGrayOrange(
        backgroundColor = GrayBackground2,
        primaryTextColor = Color.White,
        secondaryTextColor = Color.White,
        unfocusedColor = Color.LightGray,
        primaryColor = TestOrange,
        darkIcons = false
    ),
    LightGreen(
        backgroundColor = Color.White,
        primaryTextColor = Color.Black,
        secondaryTextColor = Color.Gray,
        unfocusedColor = Color.LightGray,
        primaryColor = TestGreen,
        darkIcons = true
    ),
    DarkGreen(
        backgroundColor = GrayBackground2,
        primaryTextColor = Color.White,
        secondaryTextColor = Color.White,
        unfocusedColor = Color.LightGray,
        primaryColor = TestGreen,
        darkIcons = false
    ),
    LightBlue(
        backgroundColor = Color.White,
        primaryTextColor = Color.Black,
        secondaryTextColor = Color.Gray,
        unfocusedColor = Color.LightGray,
        primaryColor = TestBlue,
        darkIcons = true
    ),

    DarkDarkGrayBlue(
        backgroundColor = GrayBackground2,
        primaryTextColor = Color.White,
        secondaryTextColor = Color.White,
        unfocusedColor = Color.LightGray,
        primaryColor = TestBlue,
        darkIcons = false
    ),
}