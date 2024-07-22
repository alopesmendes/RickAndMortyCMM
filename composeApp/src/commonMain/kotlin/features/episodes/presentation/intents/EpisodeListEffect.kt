package features.episodes.presentation.intents

import androidx.compose.runtime.Immutable
import core.util.Reducer

@Immutable
sealed interface EpisodeListEffect : Reducer.ViewEffect {
    data class NavigateToEpisodeDetail(val id: Int) : EpisodeListEffect
}