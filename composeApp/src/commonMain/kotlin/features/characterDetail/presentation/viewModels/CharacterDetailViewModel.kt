package features.characterDetail.presentation.viewModels

import androidx.lifecycle.SavedStateHandle
import core.util.BaseViewModel
import core.util.Constants
import features.characterDetail.domain.useCases.GetCharacterDetailUseCase
import features.characterDetail.presentation.intents.CharacterDetailEffect
import features.characterDetail.presentation.intents.CharacterDetailIntent
import features.characterDetail.presentation.state.CharacterDetailState

class CharacterDetailViewModel(
    savedStateHandle: SavedStateHandle,
    getCharacterDetailUseCase: GetCharacterDetailUseCase,
) : BaseViewModel<CharacterDetailState, CharacterDetailIntent, CharacterDetailEffect>(
    initialState = CharacterDetailState(),
    reducer = CharacterDetailReducer(
        getCharacterDetailUseCase = getCharacterDetailUseCase,
    ),
) {
    private val characterId = savedStateHandle.get<Int>(Constants.PARAM_CHARACTER_DETAIL)

    init {
        characterId?.let {
            sendIntent(CharacterDetailIntent.FetchCharacterDetail(it))
        }
    }
}