package features.episodes.domain.useCases

import core.entities.State
import core.entities.toFailure
import features.episodes.domain.entities.EpisodeList
import features.episodes.domain.repositories.EpisodesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetEpisodeListUseCase(private val repository: EpisodesRepository) {
    operator fun invoke(page: String? = null) : Flow<State<EpisodeList>> = flow {
        try {
            emit(State.Loading)

            val result = repository.getEpisodeList(page)
            val state = result.fold(
                onSuccess = { State.Success(it) },
                onFailure = { State.Error(it.toFailure()) },
            )

            emit(state)
        } catch (e: Exception) {
            emit(State.Error(e.toFailure()))
        }
    }
}