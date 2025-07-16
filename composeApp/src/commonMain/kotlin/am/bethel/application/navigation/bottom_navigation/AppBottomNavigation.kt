package am.bethel.application.navigation.bottom_navigation

import am.bethel.application.navigation.navigation_component.RootComponent
import am.bethel.application.settings.domain.model.AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource


@Composable
fun AppBottomNavigation(
    modifier: Modifier = Modifier,
    appTheme: AppTheme,
    currentChild: RootComponent.Child,
    onNavigateTo: (RootComponent.Configuration) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth().background(appTheme.backgroundColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        BottomNavItem.items.onEach {
            val isSelected = when (it) {
                BottomNavItem.List -> currentChild is RootComponent.Child.List
                BottomNavItem.Bookmarked -> currentChild is RootComponent.Child.Bookmarked
                BottomNavItem.Search -> currentChild is RootComponent.Child.Search
            }
            BottomNavigationItem(
                modifier = Modifier.weight(1f),
                bottomNavItem = it,
                appTheme = appTheme,
                isSelected = isSelected
            ) {
                onNavigateTo(it.config)
            }
        }
    }
}

@Composable
fun BottomNavigationItem(
    modifier: Modifier = Modifier,
    appTheme: AppTheme,
    bottomNavItem: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(bottomNavItem.icon),
            contentDescription = bottomNavItem.label,
            tint = if (isSelected) appTheme.primaryColor else appTheme.unfocusedColor
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = bottomNavItem.label,
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected) appTheme.primaryColor else appTheme.unfocusedColor
        )
    }
}
