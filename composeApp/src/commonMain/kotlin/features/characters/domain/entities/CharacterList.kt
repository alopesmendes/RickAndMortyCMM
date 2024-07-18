package features.characters.domain.entities

import core.entities.Info

data class CharacterList(
    val info: Info,
    val results: List<Character>
)
