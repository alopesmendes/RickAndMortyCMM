package features.characters.presentation.state

import androidx.compose.runtime.Immutable
import core.entities.InfoItem
import core.entities.State
import core.util.Reducer
import features.characters.domain.entities.Character
import features.characters.domain.entities.CharacterList
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class CharactersListState(
    val page: Int = 1,
    val isLoading: Boolean = false,
    val info: InfoItem = InfoItem(),
    val characters: ImmutableList<CharacterItem> = persistentListOf(),
    val error: String? = null,
): Reducer.ViewState


