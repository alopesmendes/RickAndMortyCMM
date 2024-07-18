package features.characters.mapper

import core.mapper.mapTo
import features.characters.data.models.CharacterDto
import features.characters.data.models.CharacterListDto
import features.characters.domain.entities.Character
import features.characters.domain.entities.CharacterList

inline fun CharacterListDto.mapTo(): CharacterList = CharacterList(
    info = info.mapTo(),
    results = results.mapTo(),
)

inline fun CharacterDto.mapTo(): Character = Character(
    id = id,
    name = name,
    image = image,
    gender = gender,
)

inline fun List<CharacterDto>.mapTo(): List<Character> = map { it.mapTo() }