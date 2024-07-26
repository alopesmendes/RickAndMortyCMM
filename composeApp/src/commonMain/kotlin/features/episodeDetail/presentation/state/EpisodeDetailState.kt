package features.episodeDetail.presentation.state

import androidx.compose.runtime.Immutable
import core.util.Reducer
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class EpisodeDetailState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isCharactersLoading: Boolean = false,
    val episodeDetail: EpisodeDetailItem = EpisodeDetailItem(),
    val characters: ImmutableList<EpisodeCharacterItem> = persistentListOf(),
): Reducer.ViewState