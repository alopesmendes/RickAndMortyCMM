package features.characterDetail.mapper

import features.characterDetail.data.models.CharacterDetailDto
import features.characterDetail.data.models.CharacterDetailLocationDto
import features.characterDetail.data.models.OriginDto
import features.characterDetail.domain.entities.CharacterDetail
import features.characterDetail.domain.entities.CharacterLocation
import features.characterDetail.domain.entities.Origin

fun CharacterDetailDto.mapTo(): CharacterDetail = CharacterDetail(
    id = id,
    name = name,
    gender = gender,
    image = image,
    status = status,
    species = species,
    origin = origin.mapTo(),
    location = location.mapTo()
)

fun OriginDto.mapTo(): Origin = Origin(
    name = name,
    url = url,
)

fun CharacterDetailLocationDto.mapTo(): CharacterLocation = CharacterLocation(
    name = name,
    url = url,
)

