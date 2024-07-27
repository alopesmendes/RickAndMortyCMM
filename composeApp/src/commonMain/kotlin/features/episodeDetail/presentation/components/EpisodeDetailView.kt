package features.episodeDetail.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.components.InfiniteLazyColumn
import features.episodeDetail.presentation.state.EpisodeCharacterItem
import features.episodeDetail.presentation.state.EpisodeDetailItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EpisodeDetailView(
    modifier: Modifier = Modifier,
    episodeDetail: EpisodeDetailItem,
    episodeCharacters: ImmutableList<EpisodeCharacterItem>,
    isCharactersLoading: Boolean = false,
    onCharacterClick: (Int) -> Unit = {},
) {
    Column(modifier = modifier) {
        Text(
            text = episodeDetail.airDate,
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.subtitle1,
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = episodeDetail.episode,
            style = MaterialTheme.typography.body1,
        )

        InfiniteLazyColumn(
            modifier = Modifier.fillMaxWidth(),
            loadingItem = {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            },
            loading = isCharactersLoading,
            items = episodeCharacters,
            itemKey = { it.id },
            loadMore = {},
            itemContent = {
                EpisodeCharacterListItem(
                    modifier = Modifier.fillMaxWidth(),
                    episodeCharacter = it,
                    onClick = { onCharacterClick(it.id) }
                )
            }
        )
    }
}

@Preview
@Composable
fun EpisodeDetailViewPreview() {
    EpisodeDetailView(
        episodeDetail = EpisodeDetailItem(
            id = 6990,
            name = "Gilda Sanders",
            episode = "corrumpit",
            airDate = "posuere"
        ),
        episodeCharacters = persistentListOf(
            EpisodeCharacterItem(
                id = 6990,
                name = "Gilda Sanders",
                image = "https://rickandmortyapi.com/api/character/avatar/6990.jpeg"
            ),
            EpisodeCharacterItem(
                id = 6991,
                name = "Morty Sanders",
                image = "https://rickandmortyapi.com/api/character/avatar/6991.jpeg"
            )
        )
    )
}