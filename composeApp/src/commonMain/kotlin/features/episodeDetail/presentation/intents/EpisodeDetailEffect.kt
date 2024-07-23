package features.episodeDetail.presentation.intents

import androidx.compose.runtime.Immutable
import core.util.Reducer

@Immutable
sealed interface EpisodeDetailEffect: Reducer.ViewEffect {
}