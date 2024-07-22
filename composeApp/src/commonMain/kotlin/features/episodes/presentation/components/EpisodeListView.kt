package features.episodes.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.components.InfiniteLazyColumn
import features.episodes.presentation.state.EpisodeItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EpisodeListView(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    episodes: ImmutableList<EpisodeItem>,
    loadMore: () -> Unit,
    onEpisodeClick: (EpisodeItem) -> Unit,
) {
    InfiniteLazyColumn(
        modifier = modifier,
        loading = loading,
        items = episodes,
        loadMore = loadMore,
        itemKey = { it.id },
        itemContent = {
            EpisodeListItem(
                episode = it,
                modifier = Modifier.fillMaxWidth(),
                onClick = { onEpisodeClick(it) }
            )
        }
    )
}

@Preview
@Composable
fun EpisodeListViewPreview() {
    EpisodeListView(
        episodes = (1..10).map {
            EpisodeItem(
                id = it,
                episode = "Episode $it",
                airDate = "20-07-2023",
                name = "Name $it",
            )
        }.toPersistentList(),
        onEpisodeClick = {

        },
        loadMore = {

        }
    )
}