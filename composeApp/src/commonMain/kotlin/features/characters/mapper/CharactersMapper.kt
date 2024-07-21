package features.characters.mapper

import core.entities.State
import core.mapper.mapTo
import features.characters.data.models.CharacterDto
import features.characters.data.models.CharacterListDto
import features.characters.domain.entities.Character
import features.characters.domain.entities.CharacterList
import features.characters.presentation.state.CharacterItem
import features.characters.presentation.state.CharactersListState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

fun CharacterListDto.mapTo(): CharacterList = CharacterList(
    info = info.mapTo(),
    results = results.mapTo(),
)

fun CharacterDto.mapTo(): Character = Character(
    id = id,
    name = name,
    image = image,
    gender = gender,
)

fun List<CharacterDto>.mapTo(): List<Character> = map { it.mapTo() }

fun Character.mapTo(): CharacterItem = CharacterItem(
    id = id,
    name = name,
    image = image,
    gender = gender,
)

fun List<Character>.mapTo(): ImmutableList<CharacterItem> = persistentListOf(*map { it.mapTo() }.toTypedArray())

fun State<CharacterList>.mapToCharactersListState(
    charactersListState: CharactersListState = CharactersListState()
): CharactersListState {
    return when (this) {
        is State.Loading -> charactersListState.copy(isLoading = true)
        is State.Error -> charactersListState.copy(
            error = "error",
            isLoading = false,
        )

        is State.Success -> charactersListState.copy(
            isLoading = false,
            characters = persistentListOf(
                *charactersListState.characters.toTypedArray(),
                *data.results.mapTo().toTypedArray(),
            ),
            info = data.info.mapTo(),
        )
    }
}