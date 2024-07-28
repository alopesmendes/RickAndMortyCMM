package core.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import core.entities.ScaffoldItemsState
import core.navigation.Routes
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScaffold(
    modifier: Modifier = Modifier,
    scaffoldItemsState: ScaffoldItemsState,
    onClick: (Routes) -> Unit,
    onNavigateBack: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    var selected by remember {
        mutableStateOf(scaffoldItemsState.startDestination)
    }
    Scaffold(
        modifier = modifier,
        content = content,
        topBar = {
            AnimatedVisibility(
                visible = scaffoldItemsState.topBarTitle != null,
            ) {
                MainTopBar(
                    title = scaffoldItemsState.topBarTitle ?: "",
                    onNavigateBack = onNavigateBack,
                    actionId = scaffoldItemsState.actionId,
                )
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = scaffoldItemsState.navItems.isNotEmpty(),
            ) {
                BottomNavigation(
                    modifier = Modifier,
                    backgroundColor = MaterialTheme.colors.background,
                    contentColor = MaterialTheme.colors.onBackground
                ) {
                    scaffoldItemsState.navItems.forEach { item ->
                        BottomNavigationItem(
                            icon = {
                                item.icon?.let {
                                    Icon(
                                        imageVector = it,
                                        contentDescription = null
                                    )
                                }
                            },
                            onClick = {
                                selected = item
                                onClick(item)
                            },
                            selected = item == selected,
                            label = {
                                item.stringRes?.let {
                                    Text(stringResource(it))
                                }
                            },
                            selectedContentColor = MaterialTheme.colors.primary,
                            unselectedContentColor = MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
        }
    )
}