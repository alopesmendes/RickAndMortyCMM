package features.characters.presentation.state

import androidx.compose.runtime.Immutable
import features.characters.domain.entities.Character
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class CharactersListState(
    val page: Int = 1,
    val isLoading: Boolean = false,
    val characters: ImmutableList<Character> = persistentListOf(),
    val error: String? = null,
)
