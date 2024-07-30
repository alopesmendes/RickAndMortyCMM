package features.episodeDetail.presentation.viewModels

import androidx.lifecycle.SavedStateHandle
import core.util.BaseViewModel
import core.util.Constants
import features.episodeDetail.domain.useCases.GetEpisodeCharactersUseCase
import features.episodeDetail.domain.useCases.GetEpisodeDetailUseCase
import features.episodeDetail.presentation.intents.EpisodeDetailEffect
import features.episodeDetail.presentation.intents.EpisodeDetailIntent
import features.episodeDetail.presentation.state.EpisodeDetailState

class EpisodeDetailViewModel(
    savedStateHandle: SavedStateHandle,
    getEpisodeDetailUseCase: GetEpisodeDetailUseCase,
    getEpisodeCharactersUseCase: GetEpisodeCharactersUseCase,
): BaseViewModel<EpisodeDetailState, EpisodeDetailIntent, EpisodeDetailEffect>(
    initialState = EpisodeDetailState(),
    reducer = EpisodeDetailReducer(
        getEpisodeCharactersUseCase = getEpisodeCharactersUseCase,
        getEpisodeDetailUseCase = getEpisodeDetailUseCase,
    ),
) {
    private val episodeId = savedStateHandle.get<Int>(Constants.PARAM_EPISODE_DETAIL)

    init {
        episodeId?.let {
            sendIntent(EpisodeDetailIntent.FetchEpisodeDetail(it))
        }
    }
}