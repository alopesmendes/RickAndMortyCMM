package features.characterDetail.presentation.state

import androidx.compose.runtime.Immutable

@Immutable
data class CharacterLocationItem(
    val name: String = "",
    val id: Int? = null,
)
