package features.episodes.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
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
        Row(
            modifier = Modifier.fillMaxWidth().padding(
                vertical = 4.dp,
                horizontal = 8.dp,
            ),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "#${episode.id}",
                        style = MaterialTheme.typography.h5,
                        fontFamily = FontFamily.Cursive,
                    )
                    Text(
                        text = episode.name,
                        style = MaterialTheme.typography.h5,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    episode.episode,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.primarySurface,
                )
            }
            Text(
                text = episode.airDate,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.primaryVariant,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
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