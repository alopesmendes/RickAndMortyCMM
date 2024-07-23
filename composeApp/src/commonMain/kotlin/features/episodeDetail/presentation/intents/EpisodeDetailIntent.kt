package features.episodeDetail.presentation.intents

import androidx.compose.runtime.Immutable
import core.util.Reducer

@Immutable
sealed interface EpisodeDetailIntent : Reducer.ViewIntent {
    data class FetchEpisodeDetail(val id: Int): EpisodeDetailIntent
}