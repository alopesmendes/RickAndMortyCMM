package features.episodeDetail.domain.useCases

class GetEpisodeDetailWithEpisodeCharactersUseCase(
    private val getEpisodeDetailUseCase: GetEpisodeDetailUseCase,
    private val getEpisodeCharactersUseCase: GetEpisodeCharactersUseCase
) {
}