package features.characterDetail.presentation.state

import androidx.compose.runtime.Immutable
import core.util.Reducer

@Immutable
data class CharacterDetailState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val character: CharacterDetailItem = CharacterDetailItem()
): Reducer.ViewState
