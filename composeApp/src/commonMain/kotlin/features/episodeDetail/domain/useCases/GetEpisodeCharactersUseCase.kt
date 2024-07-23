package features.episodeDetail.domain.useCases

import core.entities.State
import core.entities.toFailure
import features.episodeDetail.domain.entities.EpisodeCharacter
import features.episodeDetail.domain.repositories.EpisodeDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetEpisodeCharactersUseCase(
    private val repository: EpisodeDetailRepository
) {
    operator fun invoke(characters: List<String>): Flow<State<List<EpisodeCharacter>>> = flow {
        try {
            emit(State.Loading)
            val result = repository.getCharacters(characters)
            val state = result.fold(
                onFailure = { State.Error(it.toFailure()) },
                onSuccess = { State.Success(it) }
            )
            emit(state)
        } catch (e: Exception) {
            emit(State.Error(e.toFailure()))
        }
    }
}