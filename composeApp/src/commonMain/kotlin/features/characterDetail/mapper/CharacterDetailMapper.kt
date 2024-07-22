package features.characterDetail.mapper

import core.entities.State
import core.mapper.mapTo
import features.characterDetail.data.models.CharacterDetailDto
import features.characterDetail.data.models.CharacterDetailLocationDto
import features.characterDetail.data.models.OriginDto
import features.characterDetail.domain.entities.CharacterDetail
import features.characterDetail.domain.entities.CharacterLocation
import features.characterDetail.domain.entities.Origin
import features.characterDetail.presentation.state.CharacterDetailItem
import features.characterDetail.presentation.state.CharacterDetailState
import features.characters.domain.entities.CharacterList
import features.characters.mapper.mapTo
import features.characters.presentation.state.CharactersListState
import kotlinx.collections.immutable.persistentListOf

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

fun CharacterDetail.mapTo(): CharacterDetailItem = CharacterDetailItem(
    id = id,
    name = name,
    image = image,
    gender = gender,
    status = status,
    species = species,
)

fun State<CharacterDetail>.mapTo(
    characterDetailState: CharacterDetailState = CharacterDetailState(),
): CharacterDetailState {
    return when (this) {
        is State.Loading -> characterDetailState.copy(isLoading = true)
        is State.Error -> characterDetailState.copy(
            error = "error",
            isLoading = false,
        )

        is State.Success -> characterDetailState.copy(
            isLoading = false,
            character = data.mapTo(),
        )
    }
}
