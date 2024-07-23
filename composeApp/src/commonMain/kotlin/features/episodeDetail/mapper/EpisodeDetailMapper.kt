package features.episodeDetail.mapper

import core.entities.State
import features.episodeDetail.data.models.EpisodeDetailDto
import features.episodeDetail.domain.entities.EpisodeDetail
import features.episodeDetail.presentation.state.EpisodeDetailItem
import features.episodeDetail.presentation.state.EpisodeDetailState

fun EpisodeDetailDto.mapTo(): EpisodeDetail = EpisodeDetail(
    id = id,
    name = name,
    episode = episode,
    airDate = airDate,
    url = url,
    created = created,
)

fun EpisodeDetail.mapTo(): EpisodeDetailItem = EpisodeDetailItem(
    id = id,
    name = name,
    episode = episode,
    airDate = airDate,
)

fun State<EpisodeDetail>.mapTo(episodeDetailState: EpisodeDetailState): EpisodeDetailState {
    return when(this) {
        is State.Error -> episodeDetailState.copy(
            isLoading = false,
            error = "Error"
        )
        State.Loading -> episodeDetailState.copy(
            isLoading = true
        )
        is State.Success -> episodeDetailState.copy(
            episodeDetailItem = data.mapTo(),
        )
    }
}