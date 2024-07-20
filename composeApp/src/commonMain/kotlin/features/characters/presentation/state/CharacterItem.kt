package features.characters.presentation.state

import androidx.compose.runtime.Immutable

@Immutable
data class CharacterItem(
    val id: Int,
    val name: String,
    val gender: String,
    val image: String,
)
