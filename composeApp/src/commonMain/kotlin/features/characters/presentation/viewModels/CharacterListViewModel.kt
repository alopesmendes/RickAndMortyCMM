package features.characters.presentation.viewModels

import androidx.lifecycle.viewModelScope
import core.util.BaseViewModel
import features.characters.domain.useCases.GetCharactersListUseCase
import features.characters.mapper.mapToCharactersListState
import features.characters.presentation.intents.CharacterListEffect
import features.characters.presentation.intents.CharacterListIntent
import features.characters.presentation.state.CharactersListState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction1

class CharacterListViewModel(
    private val getCharactersListUseCase: GetCharactersListUseCase
) : BaseViewModel<CharactersListState, CharacterListIntent, CharacterListEffect>(
    initialState = CharactersListState(),
) {

    init {
        sendIntent(CharacterListIntent.FetchCharacterList())
    }

    override fun reduce(previousState: KFunction1<(CharactersListState) -> CharactersListState, Unit>, event: CharacterListIntent) {
        viewModelScope.launch {
            when(event) {
                is CharacterListIntent.FetchCharacterList -> {
                    getCharactersListUseCase(event.page).collectLatest { state ->
                        previousState.invoke(state::mapToCharactersListState)
                    }
                }
            }
        }
    }

}