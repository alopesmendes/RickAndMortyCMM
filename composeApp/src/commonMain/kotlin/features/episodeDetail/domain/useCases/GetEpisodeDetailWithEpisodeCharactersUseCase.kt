package features.episodeDetail.domain.useCases

import core.entities.State
import features.episodeDetail.domain.entities.EpisodeDetailWithEpisodeCharacters
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class GetEpisodeDetailWithEpisodeCharactersUseCase(
    private val getEpisodeDetailUseCase: GetEpisodeDetailUseCase,
    private val getEpisodeCharactersUseCase: GetEpisodeCharactersUseCase
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(id: Int): Flow<State<EpisodeDetailWithEpisodeCharacters>> = flow {
        emit(State.Loading)
        val episodeDetailWithCharactersFlow = getEpisodeDetailUseCase(id).flatMapLatest { episodeDetailState ->
            when (episodeDetailState) {
                is State.Error -> {
                    flowOf(State.Error(episodeDetailState.failure))
                }

                State.Loading -> {
                    flowOf(State.Loading)
                }

                is State.Success -> {
                    val episodeCharacters =
                        getEpisodeCharactersUseCase(episodeDetailState.data.characters).map { episodeCharactersState ->
                            when (episodeCharactersState) {
                                is State.Error -> {
                                    State.Error(episodeCharactersState.failure)
                                }

                                State.Loading -> {
                                    State.Loading
                                }

                                is State.Success -> {
                                    State.Success(
                                        EpisodeDetailWithEpisodeCharacters(
                                            episodeDetailState.data,
                                            episodeCharactersState.data
                                        )
                                    )
                                }
                            }
                        }
                    episodeCharacters
                }
            }
        }
        emitAll(episodeDetailWithCharactersFlow)
    }
}