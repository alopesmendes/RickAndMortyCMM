package features.characterDetail.presentation.state

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class CharacterDetailItem(
    val id: Int = -1,
    val name: String = "",
    val gender: String = "",
    val image: String = "",
    val status: String = "",
    val species: String = "",
    val episodes: ImmutableList<Int> = persistentListOf(),
    val location: CharacterLocationItem = CharacterLocationItem(),
    val origin: CharacterLocationItem = CharacterLocationItem(),
)
