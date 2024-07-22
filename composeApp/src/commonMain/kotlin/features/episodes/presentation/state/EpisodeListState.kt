package features.episodes.presentation.state

import androidx.compose.runtime.Immutable
import core.entities.InfoItem
import core.util.Reducer
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class EpisodeListState(
    val isLoading: Boolean = false,
    val info: InfoItem = InfoItem(),
    val episodes: ImmutableList<EpisodeItem> = persistentListOf(),
    val error: String? = null
): Reducer.ViewState
