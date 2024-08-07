package features.characters.presentation.intents

import androidx.compose.runtime.Immutable
import core.util.Reducer

@Immutable
sealed interface CharacterListEffect: Reducer.ViewEffect {
    data class NavigateToCharacterDetail(val id: Int): CharacterListEffect
}
