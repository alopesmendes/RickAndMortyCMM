package features.locations.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import core.entities.InfoItem
import features.locations.presentation.state.LocationItem
import features.locations.presentation.state.LocationListState
import kotlinx.collections.immutable.toPersistentList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LocationListContent(
    modifier: Modifier = Modifier,
    state: LocationListState,
    loadMore: () -> Unit,
    onLocationClick: (Int) -> Unit,
) {
    when {
        state.error != null -> {
            Text(
                state.error,
                modifier = modifier.fillMaxSize(),
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.error,
            )
        }
        else -> {
            LocationListView(
                loading = state.isLoading,
                modifier = modifier.fillMaxSize(),
                locations = state.locations,
                onLocationClick = { onLocationClick(it.id) },
                loadMore = loadMore,
            )
        }
    }
}

@Preview
@Composable
fun LocationListContentPreview() {
    LocationListContent(
        state = LocationListState(
            info = InfoItem(
                count = 8641, pages = 3933, next = null, prev = null
            ),
            locations = (1..10).map {
                LocationItem(
                    id = it,
                    name = "Location $it",
                    type = "Type $it",
                    dimension = "Dimension $it"
                )
            }.toPersistentList()
        ),
        loadMore = {},
        onLocationClick = {}
    )
}