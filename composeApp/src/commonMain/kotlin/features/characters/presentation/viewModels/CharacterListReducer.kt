package features.characters.presentation.viewModels

import core.util.Reducer
import features.characters.domain.useCases.GetCharactersListUseCase
import features.characters.mapper.mapToCharactersListState
import features.characters.presentation.intents.CharacterListEffect
import features.characters.presentation.intents.CharacterListIntent
import features.characters.presentation.state.CharactersListState
import kotlinx.coroutines.flow.collectLatest
import kotlin.reflect.KFunction1

class CharacterListReducer(
    private val getCharactersListUseCase: GetCharactersListUseCase,
): Reducer<CharactersListState, CharacterListIntent, CharacterListEffect> {
    override suspend fun reduce(
        updateState: KFunction1<(CharactersListState) -> CharactersListState, Unit>,
        intent: CharacterListIntent
    ) {
        when(intent) {
            is CharacterListIntent.FetchCharacterList -> {
                getCharactersListUseCase(intent.page).collectLatest { state ->
                    updateState.invoke(state::mapToCharactersListState)
                }
            }
        }
    }
}