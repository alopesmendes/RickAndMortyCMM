package features.episodeDetail.presentation.viewModels

import core.util.Reducer
import features.episodeDetail.domain.useCases.GetEpisodeDetailUseCase
import features.episodeDetail.domain.useCases.GetEpisodeDetailWithEpisodeCharactersUseCase
import features.episodeDetail.mapper.mapTo
import features.episodeDetail.presentation.intents.EpisodeDetailEffect
import features.episodeDetail.presentation.intents.EpisodeDetailIntent
import features.episodeDetail.presentation.state.EpisodeDetailState
import kotlinx.coroutines.flow.collectLatest
import kotlin.reflect.KFunction1

class EpisodeDetailReducer(
    private val getEpisodeDetailWithCharactersUseCase: GetEpisodeDetailWithEpisodeCharactersUseCase,
): Reducer<EpisodeDetailState, EpisodeDetailIntent, EpisodeDetailEffect> {
    override suspend fun reduce(
        updateState: KFunction1<(EpisodeDetailState) -> EpisodeDetailState, Unit>,
        intent: EpisodeDetailIntent
    ) {
        when (intent) {
            is EpisodeDetailIntent.FetchEpisodeDetailWithCharacters -> {
                getEpisodeDetailWithCharactersUseCase(intent.id).collectLatest { state ->
                    updateState.invoke(state::mapTo)
                }
            }
        }
    }
}