package features.characters.presentation.viewModels

import core.util.BaseViewModel
import features.characters.domain.useCases.GetCharactersListUseCase
import features.characters.presentation.intents.CharacterListEffect
import features.characters.presentation.intents.CharacterListIntent
import features.characters.presentation.state.CharactersListState

class CharacterListViewModel(
    getCharactersListUseCase: GetCharactersListUseCase
) : BaseViewModel<CharactersListState, CharacterListIntent, CharacterListEffect>(
    initialState = CharactersListState(),
    reducer = CharacterListReducer(
        getCharactersListUseCase = getCharactersListUseCase,
    ),
) {

    init {
        sendIntent(CharacterListIntent.FetchCharacterList())
    }

}