package features.characterDetail.mapper

import core.entities.State
import core.util.Tools.extractIdFromUrl
import features.characterDetail.data.models.CharacterDetailDto
import features.characterDetail.data.models.CharacterDetailLocationDto
import features.characterDetail.domain.entities.CharacterDetail
import features.characterDetail.domain.entities.CharacterLocation
import features.characterDetail.presentation.state.CharacterDetailItem
import features.characterDetail.presentation.state.CharacterDetailState
import features.characterDetail.presentation.state.CharacterLocationItem
import kotlinx.collections.immutable.toImmutableList

fun CharacterDetailDto.mapTo(): CharacterDetail = CharacterDetail(
    id = id,
    name = name,
    gender = gender,
    image = image,
    status = status,
    species = species,
    origin = origin.mapTo(),
    location = location.mapTo(),
    episodes = episodes
)

fun CharacterDetailLocationDto.mapTo(): CharacterLocation = CharacterLocation(
    name = name,
    url = url,
)

fun CharacterLocation.mapTo(): CharacterLocationItem = CharacterLocationItem(
    id = url.extractIdFromUrl(),
    name = name,
)

fun CharacterDetail.mapTo(): CharacterDetailItem = CharacterDetailItem(
    id = id,
    name = name,
    image = image,
    gender = gender,
    status = status,
    species = species,
    episodes = episodes.mapNotNull { it.extractIdFromUrl() }.toImmutableList(),
    location = location.mapTo(),
    origin = origin.mapTo(),
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
