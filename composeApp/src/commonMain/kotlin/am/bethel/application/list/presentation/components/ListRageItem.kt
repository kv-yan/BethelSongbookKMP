package am.bethel.application.list.presentation.components

import am.betel.songbook.common.presentation.ui.theme.Shape16
import am.bethel.application.common.presentation.components.ui.FontBold
import am.bethel.application.common.presentation.components.ui.FontRegular
import am.bethel.application.settings.domain.model.AppTheme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bethelsongbookkmp.composeapp.generated.resources.Res
import bethelsongbookkmp.composeapp.generated.resources.ic_keyboard_arrow_down
import bethelsongbookkmp.composeapp.generated.resources.ic_keyboard_arrow_up
import bethelsongbookkmp.composeapp.generated.resources.ic_music
import bethelsongbookkmp.composeapp.generated.resources.items_range
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun ListRageItem(
    modifier: Modifier = Modifier,
    appTheme: AppTheme,
    intRange: IntRange,
    initialExpanded: Boolean = false,
    navigateToDetails: (Int) -> Unit = {},
) {
    var isExpanded by rememberSaveable { mutableStateOf(initialExpanded) }
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_music),
                contentDescription = null,
                tint = appTheme.primaryColor
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                text = stringResource(Res.string.items_range, intRange.first, intRange.last),
                style = TextStyle(
                    fontFamily = FontRegular(),
                    fontSize = 16.sp,
                    color = appTheme.primaryTextColor
                )
            )

            IconButton(
                onClick = { isExpanded = !isExpanded }
            ) {
                Icon(
                    painter = painterResource(if (isExpanded) Res.drawable.ic_keyboard_arrow_up else Res.drawable.ic_keyboard_arrow_down),
                    contentDescription = null,
                    tint = appTheme.primaryTextColor
                )
            }
        }

        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = isExpanded
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 0.5.dp,
                        color = appTheme.primaryTextColor,
                        shape = Shape16
                    )
                    .padding(4.dp)
                    .heightIn(
                        min = 0.dp,
                        max = 2000.dp
                    ),
                columns = GridCells.Fixed(4),
            ) {
                items(intRange.toList(), key = { it }) {
                    TextButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { navigateToDetails(it) },
                        shape = RoundedCornerShape(0),
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor = appTheme.backgroundColor,
                                contentColor = appTheme.primaryTextColor
                            )
                    ) {
                        Text(
                            text = it.toString(),
                            style = TextStyle(
                                fontFamily = FontBold(),
                                fontSize = 15.sp,
                                color = appTheme.primaryTextColor
                            )
                        )
                    }
                }
            }
        }
    }
}