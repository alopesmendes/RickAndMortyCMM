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
import core.navigation.Routes
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScaffold(
    modifier: Modifier = Modifier,
    navItemsVisible: Boolean = false,
    startDestination: Routes,
    navItems: ImmutableList<Routes>,
    onClick: (Routes) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    var selected by remember {
        mutableStateOf(startDestination)
    }
    Scaffold(
        modifier = modifier,
        content = content,
        bottomBar = {
            AnimatedVisibility(
                visible = navItemsVisible
            ) {
                BottomNavigation(
                    modifier = Modifier,
                    backgroundColor = MaterialTheme.colors.background,
                    contentColor = MaterialTheme.colors.onBackground
                ) {
                    navItems.forEach { item ->
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