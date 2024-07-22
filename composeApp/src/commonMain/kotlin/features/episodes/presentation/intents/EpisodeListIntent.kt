package features.episodes.presentation.intents

import androidx.compose.runtime.Immutable
import core.entities.InfoItem
import core.util.Reducer

@Immutable
sealed interface EpisodeListIntent: Reducer.ViewIntent {
    data class FetchEpisodes(val page: String? = null, val hasMore: Boolean = true) : EpisodeListIntent
}
