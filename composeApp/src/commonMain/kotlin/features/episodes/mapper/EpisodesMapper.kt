package features.episodes.mapper

import core.entities.State
import core.mapper.mapTo
import features.episodes.data.models.EpisodeDto
import features.episodes.data.models.EpisodeListDto
import features.episodes.domain.entities.Episode
import features.episodes.domain.entities.EpisodeList
import features.episodes.presentation.state.EpisodeItem
import features.episodes.presentation.state.EpisodeListState
import kotlinx.collections.immutable.persistentListOf

fun EpisodeListDto.mapTo(): EpisodeList = EpisodeList(
    info = info.mapTo(),
    results = results.mapTo(),
)

fun EpisodeDto.mapTo(): Episode = Episode(
    id = id,
    name = name,
    episode = episode,
    airDate = airDate,
    url = url,
)

fun List<EpisodeDto>.mapTo(): List<Episode> = map { it.mapTo() }

fun Episode.mapTo(): EpisodeItem = EpisodeItem(
    id = id,
    name = name,
    episode = episode,
    airDate = airDate,
    url = url,
)

fun State<EpisodeList>.mapTo(episodeListState: EpisodeListState): EpisodeListState {
    return when (this) {
        is State.Loading -> episodeListState.copy(isLoading = true)
        is State.Success -> episodeListState.copy(
            isLoading = false,
            info = data.info.mapTo(),
            episodes = persistentListOf(
                *episodeListState.episodes.toTypedArray(),
                *data.results.map { it.mapTo() }.toTypedArray()
            ),
        )
        is State.Error -> EpisodeListState(
            isLoading = false,
            error = "Error",
        )
    }
}