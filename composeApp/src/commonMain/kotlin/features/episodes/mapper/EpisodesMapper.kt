package features.episodes.mapper

import core.mapper.mapTo
import features.episodes.data.models.EpisodeDto
import features.episodes.data.models.EpisodeListDto
import features.episodes.domain.entities.Episode
import features.episodes.domain.entities.EpisodeList

inline fun EpisodeListDto.mapTo(): EpisodeList = EpisodeList(
    info = info.mapTo(),
    results = results.mapTo(),
)

inline fun EpisodeDto.mapTo(): Episode = Episode(
    id = id,
    name = name,
    episode = episode,
    airDate = airDate,
    characters = characters,
    url = url,
)

inline fun List<EpisodeDto>.mapTo(): List<Episode> = map { it.mapTo() }

