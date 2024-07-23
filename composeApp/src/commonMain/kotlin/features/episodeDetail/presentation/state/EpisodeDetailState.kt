package features.episodeDetail.presentation.state

import androidx.compose.runtime.Immutable
import core.util.Reducer

@Immutable
data class EpisodeDetailState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val episodeDetail: EpisodeDetailItem = EpisodeDetailItem()
): Reducer.ViewState
