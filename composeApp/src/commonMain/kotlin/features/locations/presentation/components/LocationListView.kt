package features.locations.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.components.InfiniteLazyColumn
import features.locations.presentation.state.LocationItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LocationListView(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    locations: ImmutableList<LocationItem>,
    onLocationClick: (LocationItem) -> Unit,
    loadMore: () -> Unit,
) {
    InfiniteLazyColumn(
        modifier = modifier,
        loading = loading,
        loadingItem = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        },
        loadMore = loadMore,
        items = locations,
        itemKey = { it.id },
        itemContent = {
            LocationListItem(
                modifier = Modifier.fillMaxWidth(),
                location = it,
                onClick = { onLocationClick(it) }
            )
        }
    )
}

@Preview
@Composable
fun LocationListViewPreview() {
    LocationListView(
        loading = false,
        locations = (1..10).map {
            LocationItem(
                id = it,
                name = "Location $it",
                type = "Type $it",
                dimension = "Dimension $it",
            )
        }.toPersistentList(),
        loadMore = {},
        onLocationClick = {}
    )

}