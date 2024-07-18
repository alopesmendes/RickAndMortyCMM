package features.episodeDetail.domain.entities

data class EpisodeDetailWithEpisodeCharacters(
    val episodeDetail: EpisodeDetail,
    val episodeCharacters: List<EpisodeCharacter>,
)
