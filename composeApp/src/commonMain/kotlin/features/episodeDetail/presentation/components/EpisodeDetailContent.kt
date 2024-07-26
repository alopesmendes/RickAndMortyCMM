package features.episodeDetail.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import features.episodeDetail.presentation.state.EpisodeDetailItem
import features.episodeDetail.presentation.state.EpisodeDetailState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EpisodeDetailContent(
    modifier: Modifier = Modifier,
    episodeDetailState: EpisodeDetailState,
    onCharacterClick: (Int) -> Unit = {},
) {
    when {
        episodeDetailState.error != null -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = episodeDetailState.error, color = MaterialTheme.colors.error)
            }
        }
        else -> {
            EpisodeDetailView(
                modifier = modifier,
                episodeDetail = episodeDetailState.episodeDetail,
                episodeCharacters = episodeDetailState.characters,
                isCharactersLoading = episodeDetailState.isCharactersLoading,
                onCharacterClick = onCharacterClick,
            )
        }
    }
}

@Preview
@Composable
fun EpisodeDetailContentPreview() {
    EpisodeDetailContent(
        episodeDetailState = EpisodeDetailState(
            isLoading = false,
            error = null,
            episodeDetail = EpisodeDetailItem(
                id = 8232,
                name = "Isabelle McMahon",
                episode = "quidam",
                airDate = "est"
            )
        )
    )
}