package features.episodeDetail.mapper

import features.episodeDetail.data.models.EpisodeDetailDto
import features.episodeDetail.domain.entities.EpisodeDetail

fun EpisodeDetailDto.mapTo(): EpisodeDetail = EpisodeDetail(
    id = id,
    name = name,
    episode = episode,
    airDate = airDate,
    url = url,
    created = created,
)