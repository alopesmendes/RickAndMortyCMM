package features.episodes.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import features.episodes.presentation.state.EpisodeItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EpisodeListItem(
    modifier: Modifier = Modifier,
    episode: EpisodeItem,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.padding(horizontal = 4.dp),
        onClick = onClick,
    ) {

    }
}

@Preview
@Composable
fun EpisodeListItemPreview() {
    EpisodeListItem(
        episode = EpisodeItem(
            id = 1,
            name = "Episode 1",
            airDate = "2017-11-10",
            episode = "S01E01",
        ),
        onClick = {

        }
    )
}