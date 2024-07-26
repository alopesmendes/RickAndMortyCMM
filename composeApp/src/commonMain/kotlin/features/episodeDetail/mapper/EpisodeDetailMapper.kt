package features.episodeDetail.mapper

import core.entities.State
import features.episodeDetail.data.models.EpisodeDetailCharacterDto
import features.episodeDetail.data.models.EpisodeDetailDto
import features.episodeDetail.domain.entities.EpisodeCharacter
import features.episodeDetail.domain.entities.EpisodeDetail
import features.episodeDetail.presentation.state.EpisodeCharacterItem
import features.episodeDetail.presentation.state.EpisodeDetailItem
import features.episodeDetail.presentation.state.EpisodeDetailState
import kotlinx.collections.immutable.persistentListOf
import kotlin.jvm.JvmName

fun EpisodeDetailDto.mapTo(): EpisodeDetail = EpisodeDetail(
    id = id,
    name = name,
    episode = episode,
    airDate = airDate,
    url = url,
    created = created,
    characters = characters,
)

fun EpisodeDetail.mapTo(): EpisodeDetailItem = EpisodeDetailItem(
    id = id,
    name = name,
    episode = episode,
    airDate = airDate,
)

fun EpisodeDetailCharacterDto.mapTo(): EpisodeCharacter = EpisodeCharacter(
    id = id,
    name = name,
    image = image,
)

fun List<EpisodeDetailCharacterDto>.mapTo(): List<EpisodeCharacter> = map { it.mapTo() }

fun EpisodeCharacter.mapTo(): EpisodeCharacterItem = EpisodeCharacterItem(
    id = id,
    name = name,
    image = image,
)

@JvmName("mapToEpisodeDetailState")
fun State<EpisodeDetail>.mapTo(episodeDetailState: EpisodeDetailState): EpisodeDetailState {
    return when(this) {
        is State.Error -> episodeDetailState.copy(
            isLoading = false,
            error = "Error"
        )
        State.Loading -> episodeDetailState.copy(
            isLoading = true
        )
        is State.Success -> episodeDetailState.copy(
            isLoading = false,
            episodeDetail = data.mapTo(),
        )
    }
}

@JvmName("mapToEpisodeDetailStateCharacters")
fun State<List<EpisodeCharacter>>.mapTo(episodeDetailState: EpisodeDetailState): EpisodeDetailState {
    return when(this) {
        is State.Error -> episodeDetailState.copy(
            isCharactersLoading = false,
            error = "Error"
        )
        State.Loading -> episodeDetailState.copy(
            isCharactersLoading = true
        )
        is State.Success -> episodeDetailState.copy(
            isCharactersLoading = false,
            characters = persistentListOf(
                *episodeDetailState.characters.toTypedArray(),
                *data.map { it.mapTo() }.toTypedArray(),
            )
        )
    }
}