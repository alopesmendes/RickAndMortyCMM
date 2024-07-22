package features.episodes.presentation.viewModels

import core.util.BaseViewModel
import features.episodes.domain.useCases.GetEpisodeListUseCase
import features.episodes.presentation.intents.EpisodeListEffect
import features.episodes.presentation.intents.EpisodeListIntent
import features.episodes.presentation.state.EpisodeListState

class EpisodeListViewModel(
    getEpisodeListUseCase: GetEpisodeListUseCase
): BaseViewModel<EpisodeListState, EpisodeListIntent, EpisodeListEffect>(
    initialState = EpisodeListState(),
    reducer = EpisodeListReducer(
        getEpisodeListUseCase = getEpisodeListUseCase
    )
) {
    init {
        sendIntent(EpisodeListIntent.FetchEpisodes())
    }
}