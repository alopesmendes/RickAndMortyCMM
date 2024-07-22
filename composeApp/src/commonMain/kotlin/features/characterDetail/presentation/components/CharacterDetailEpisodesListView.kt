package features.characterDetail.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.components.InfiniteLazyColumn
import core.components.TitleComponent
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import rickandmorty.composeapp.generated.resources.Res
import rickandmorty.composeapp.generated.resources.episode_item
import rickandmorty.composeapp.generated.resources.episodes

@Composable
fun CharacterDetailEpisodesListView(
    modifier: Modifier = Modifier,
    episodes: ImmutableList<Int>,
    onEpisodeClick: (Int) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TitleComponent(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(Res.string.episodes),
        )

        InfiniteLazyColumn(
            modifier = Modifier.weight(1f),
            items = episodes,
            itemKey = { it },
            itemContent = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onEpisodeClick(it) },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(Res.string.episode_item, it),
                        style = MaterialTheme.typography.body1
                    )
                    IconButton(
                        onClick = { onEpisodeClick(it) },
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primarySurface
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun CharacterDetailEpisodesListViewPreview() {
    CharacterDetailEpisodesListView(
        onEpisodeClick = {},
        episodes = persistentListOf(
            1,
            2,
            3,
        )
    )
}