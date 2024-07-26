package features.episodeDetail.presentation.viewModels

import core.entities.State
import core.util.Reducer
import features.episodeDetail.domain.useCases.GetEpisodeCharactersUseCase
import features.episodeDetail.domain.useCases.GetEpisodeDetailUseCase
import features.episodeDetail.mapper.mapTo
import features.episodeDetail.presentation.intents.EpisodeDetailEffect
import features.episodeDetail.presentation.intents.EpisodeDetailIntent
import features.episodeDetail.presentation.state.EpisodeDetailState
import kotlinx.coroutines.flow.collectLatest
import kotlin.reflect.KFunction1

class EpisodeDetailReducer(
    private val getEpisodeDetailUseCase: GetEpisodeDetailUseCase,
    private val getEpisodeCharactersUseCase: GetEpisodeCharactersUseCase,
): Reducer<EpisodeDetailState, EpisodeDetailIntent, EpisodeDetailEffect> {
    override suspend fun reduce(
        updateState: KFunction1<(EpisodeDetailState) -> EpisodeDetailState, Unit>,
        intent: EpisodeDetailIntent,
        sendIntent: (EpisodeDetailIntent) -> Unit,
    ) {
        when (intent) {
            is EpisodeDetailIntent.FetchEpisodeCharacters -> {
                getEpisodeCharactersUseCase.invoke(intent.characters).collectLatest { state ->
                    updateState.invoke(state::mapTo)
                }
            }
            is EpisodeDetailIntent.FetchEpisodeDetail -> {
                getEpisodeDetailUseCase(intent.id).collectLatest { state ->
                    if (state is State.Success) {
                        sendIntent(EpisodeDetailIntent.FetchEpisodeCharacters(state.data.characters))
                    }
                    updateState.invoke(state::mapTo)
                }
            }
        }
    }
}