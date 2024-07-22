package features.characterDetail.presentation.intents

import androidx.compose.runtime.Immutable
import core.util.Reducer

@Immutable
sealed class CharacterDetailIntent: Reducer.ViewIntent {
    data class FetchCharacterDetail(val id: Int): CharacterDetailIntent()
}
