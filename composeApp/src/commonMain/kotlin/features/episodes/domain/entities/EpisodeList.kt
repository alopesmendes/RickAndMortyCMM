package features.episodes.domain.entities

import core.entities.Info

data class EpisodeList(
    val info: Info,
    val results: List<Episode>,
)
