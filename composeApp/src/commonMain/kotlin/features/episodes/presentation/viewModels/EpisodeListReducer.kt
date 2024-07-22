package features.episodes.presentation.viewModels

import core.util.Reducer
import features.episodes.domain.useCases.GetEpisodeListUseCase
import features.episodes.mapper.mapTo
import features.episodes.presentation.intents.EpisodeListEffect
import features.episodes.presentation.intents.EpisodeListIntent
import features.episodes.presentation.state.EpisodeListState
import kotlinx.coroutines.flow.collectLatest
import kotlin.reflect.KFunction1

class EpisodeListReducer(
    private val getEpisodeListUseCase: GetEpisodeListUseCase,
): Reducer<EpisodeListState, EpisodeListIntent, EpisodeListEffect> {
    override suspend fun reduce(
        updateState: KFunction1<(EpisodeListState) -> EpisodeListState, Unit>,
        intent: EpisodeListIntent
    ) {
        when(intent) {
            is EpisodeListIntent.FetchEpisodes -> {
                if (!intent.hasMore) {
                    return
                }

                getEpisodeListUseCase(intent.page).collectLatest { state ->
                    updateState.invoke(state::mapTo)
                }
            }
        }
    }
}