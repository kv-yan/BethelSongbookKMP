package am.bethel.application.list.presentation

import am.bethel.application.common.presentation.components.inputFeald.AppInputField
import am.bethel.application.list.presentation.components.ListRageItem
import am.bethel.application.settings.domain.model.AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bethelsongbookkmp.composeapp.generated.resources.Res
import bethelsongbookkmp.composeapp.generated.resources.enter_number
import bethelsongbookkmp.composeapp.generated.resources.search
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    appTheme: AppTheme,
    viewModel: ListViewModel = koinInject(),
    navigateToDetails: (Int) -> Unit
) {
    val songsIntRange by viewModel.songsCount.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = appTheme.backgroundColor,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(top = 8.dp)
        ) {
            AppInputField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.enter_number),
                searchQuery = searchQuery,
                appTheme = appTheme,
                onValueChange = viewModel::setSearchQuery,
                trailingIcon = {
                    TextButton(
                        enabled = searchQuery.isNotEmpty(),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = if (searchQuery.isNotEmpty()) appTheme.primaryColor else appTheme.unfocusedColor,
                            disabledContentColor = appTheme.unfocusedColor
                        ),
                        onClick = {
                            navigateToDetails(searchQuery.toInt())
                        }
                    ) {
                        Text(stringResource(Res.string.search))
                    }
                },
                keyboardOptions = KeyboardOptions.Default,
                keyboardActions = KeyboardActions(
                    onSearch = {
                        navigateToDetails(searchQuery.toInt())
                    }
                )
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(
                        color = appTheme.backgroundColor,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(
                        width = 0.5.dp,
                        color = appTheme.unfocusedColor,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(
                    items = songsIntRange, key = { index, _ -> index }) { _, range ->
                    ListRageItem(
                        modifier = Modifier.fillMaxWidth(),
                        intRange = range,
                        appTheme = appTheme,
                        initialExpanded = false,
                        navigateToDetails = navigateToDetails
                    )
                }
            }
        }
    }
}