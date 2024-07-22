package features.characterDetail.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import features.characterDetail.domain.entities.CharacterLocation
import features.characterDetail.presentation.state.CharacterDetailItem
import features.characterDetail.presentation.state.CharacterDetailState
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import rickandmorty.composeapp.generated.resources.Res
import rickandmorty.composeapp.generated.resources.location
import rickandmorty.composeapp.generated.resources.location_item
import rickandmorty.composeapp.generated.resources.origin

@Composable
fun CharacterDetailContent(
    modifier: Modifier = Modifier,
    characterDetailState: CharacterDetailState,
    onEpisodeClick: (Int) -> Unit,
    onLocationClick: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()

    when {
        characterDetailState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        characterDetailState.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = characterDetailState.error, color = MaterialTheme.colors.error)
            }
        }

        else -> {
            val characterDetailItem = remember { characterDetailState.character }
            Column(
                modifier = modifier.verticalScroll(scrollState).padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CharacterDetailImage(
                    imageUrl = characterDetailItem.image,
                    modifier = Modifier.weight(2f),
                )
                CharacterDetailInfo(
                    characterDetailItem = characterDetailItem,
                )
                CharacterDetailLocation(
                    location = characterDetailItem.location,
                    origin = characterDetailItem.origin,
                    onLocationClick = onLocationClick,
                )
                CharacterDetailEpisodesListView(
                    modifier = Modifier.weight(1f),
                    episodes = characterDetailItem.episodes,
                    onEpisodeClick = onEpisodeClick,
                )
            }
        }
    }

}

@Preview
@Composable
fun CharacterDetailContentPreview() {
    CharacterDetailContent(
        characterDetailState = CharacterDetailState(
            isLoading = false,
            character = CharacterDetailItem(
                id = 4626,
                name = "Morgan Woods",
                gender = "euismod",
                image = "appareat",
                status = "explicari",
                species = "intellegebat",
                episodes = persistentListOf(1, 2, 3)
            )
        ),
        onEpisodeClick = { },
        onLocationClick = { },
    )
}