package features.episodeDetail.domain.useCases

import features.episodeDetail.domain.repositories.EpisodeDetailRepository

class GetEpisodeCharactersUseCase(
    private val repository: EpisodeDetailRepository
) {
}