package features.characters.presentation.intents

import androidx.compose.runtime.Immutable
import core.util.Reducer

@Immutable
sealed interface CharacterListIntent: Reducer.ViewIntent {
    data class FetchCharacterList(val page: String? = null) : CharacterListIntent
}