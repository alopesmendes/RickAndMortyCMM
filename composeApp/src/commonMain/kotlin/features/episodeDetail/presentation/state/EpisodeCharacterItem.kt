package features.episodeDetail.presentation.state

import androidx.compose.runtime.Immutable

@Immutable
data class EpisodeCharacterItem(
    val id: Int = -1,
    val name: String = "",
    val image: String = "",
)
