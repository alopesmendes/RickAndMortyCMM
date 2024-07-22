package features.episodes.presentation.state

import androidx.compose.runtime.Immutable

@Immutable
data class EpisodeItem(
    val id: Int = -1,
    val name: String = "",
    val episode: String = "",
    val airDate: String = "",
)
