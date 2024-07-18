package features.episodeDetail.domain.useCases

import features.episodeDetail.domain.repositories.EpisodeDetailRepository

class GetEpisodeDetailUseCase(
    private val repository: EpisodeDetailRepository
) {
}