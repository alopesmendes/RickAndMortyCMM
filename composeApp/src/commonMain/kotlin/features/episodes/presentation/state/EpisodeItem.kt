package features.episodes.presentation.state

import androidx.compose.runtime.Immutable

@Immutable
data class EpisodeItem(
    val id: Int,
    val name: String,
    val episode: String,
    val airDate: String,
    val url: String,
)
