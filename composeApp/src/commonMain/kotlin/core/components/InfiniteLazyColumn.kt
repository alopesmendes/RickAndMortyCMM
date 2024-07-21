package core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList

internal fun LazyListState.reachedBottom(buffer: Int = 1): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - buffer
}

@Composable
fun <T> InfiniteLazyColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    contentPadding: PaddingValues = PaddingValues(4.dp),
    loading: Boolean = false,
    state: LazyListState = rememberLazyListState(),
    items: ImmutableList<T>,
    itemKey: (T) -> Any,
    itemContent: @Composable LazyItemScope.(item: T) -> Unit,
    loadingItem: @Composable LazyItemScope.() -> Unit,
    loadMore: () -> Unit
) {
    val reachBottom by remember {
        derivedStateOf { state.reachedBottom() }
    }
    LaunchedEffect(reachBottom) {
        if (reachBottom && !loading) {
            loadMore()
        }
    }

    LazyColumn(
        modifier = modifier,
        state = state,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        contentPadding = contentPadding,
    ) {
        items(items, key = itemKey, itemContent = itemContent)

        if (loading) {
            item(content = loadingItem)
        }
    }
}