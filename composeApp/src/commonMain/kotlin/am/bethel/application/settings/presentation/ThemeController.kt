package am.bethel.application.settings.presentation

import am.bethel.application.settings.domain.model.AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThemeController(
    modifier: Modifier = Modifier,
    currentTheme: AppTheme,
    availableThemes: List<AppTheme>,
    onClick: (AppTheme) -> Unit = {},
) {
    val rowState = rememberLazyListState()
    LaunchedEffect(Unit) {
        val currentIndex = availableThemes.indexOf(currentTheme)
        rowState.scrollToItem(currentIndex)
    }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        state = rowState
    ) {
        items(availableThemes, key = { it.ordinal }) {
            ThemeItem(
                modifier = Modifier.padding(horizontal = 8.dp),
                appTheme = it,
                isSelected = currentTheme == it,
                onClick = onClick
            )
        }
    }
}

@Composable
fun ThemeItem(
    modifier: Modifier = Modifier,
    appTheme: AppTheme,
    isSelected: Boolean,
    onClick: (AppTheme) -> Unit,
) {
    Card(
        modifier = modifier.size(width = 90.dp, height = 100.dp),
        colors = CardDefaults.cardColors(
            containerColor = appTheme.backgroundColor
        ),
        onClick = {
            onClick(appTheme)
        },
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(9.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .height(3.dp)
                    .fillMaxWidth(0.8f)
                    .background(appTheme.primaryColor)
            )
            Spacer(
                modifier = Modifier
                    .height(3.dp)
                    .fillMaxWidth(0.6f)
                    .background(appTheme.primaryColor)
            )
            Spacer(
                modifier = Modifier
                    .height(3.dp)
                    .fillMaxWidth(0.4f)
                    .background(appTheme.primaryColor)
            )

            RadioButton(
                colors = RadioButtonDefaults.colors(
                    selectedColor = appTheme.primaryColor,
                    unselectedColor = appTheme.unfocusedColor,
                    disabledSelectedColor = appTheme.unfocusedColor,
                    disabledUnselectedColor = appTheme.unfocusedColor
                ),
                selected = isSelected, onClick = {
                    onClick(appTheme)
                }
            )
        }
    }
}

