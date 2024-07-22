package features.episodes.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import features.episodes.presentation.state.EpisodeItem
import features.episodes.presentation.state.EpisodeListState
import kotlinx.collections.immutable.toPersistentList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EpisodeListContent(
    modifier: Modifier = Modifier,
    episodeListState: EpisodeListState,
    onEpisodeClick: (Int) -> Unit,
    loadMore: () -> Unit
) {
    when {
        episodeListState.error != null -> {
            Text(
                episodeListState.error,
                modifier = modifier.fillMaxSize(),
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.error,
            )
        }
        else -> {
            EpisodeListView(
                modifier = modifier,
                episodes = episodeListState.episodes,
                onEpisodeClick = {
                    onEpisodeClick(it.id)
                },
                loadMore = loadMore,
                loading = episodeListState.isLoading,
            )
        }
    }
}

@Preview
@Composable
fun EpisodeListContentPreview() {
    EpisodeListContent(
        episodeListState = EpisodeListState(
            episodes = (1..10).map {
                EpisodeItem(
                    id = it,
                    name = "Episode $it",
                    airDate = "2022-01-01",
                    episode = "Episode $it",
                )
            }.toPersistentList(),
            isLoading = false,
            error = null,
        ),
        onEpisodeClick = {},
        loadMore = {}
    )
}