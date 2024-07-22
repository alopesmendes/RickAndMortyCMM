package features.characterDetail.presentation.state

import androidx.compose.runtime.Immutable

@Immutable
data class CharacterDetailItem(
    val id: Int = -1,
    val name: String = "",
    val gender: String = "",
    val image: String = "",
    val status: String = "",
    val species: String = "",
)
