package features.characterDetail.presentation.viewModels

import core.util.Reducer
import features.characterDetail.domain.useCases.GetCharacterDetailUseCase
import features.characterDetail.mapper.mapTo
import features.characterDetail.presentation.intents.CharacterDetailEffect
import features.characterDetail.presentation.intents.CharacterDetailIntent
import features.characterDetail.presentation.state.CharacterDetailState
import kotlinx.coroutines.flow.collectLatest
import kotlin.reflect.KFunction1

class CharacterDetailReducer(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
): Reducer<CharacterDetailState, CharacterDetailIntent, CharacterDetailEffect> {
    override suspend fun reduce(
        updateState: KFunction1<(CharacterDetailState) -> CharacterDetailState, Unit>,
        intent: CharacterDetailIntent
    ) {
        when(intent) {
            is CharacterDetailIntent.FetchCharacterDetail -> {
                getCharacterDetailUseCase(intent.id).collectLatest { state ->
                    updateState.invoke(state::mapTo)
                }
            }
        }
    }
}