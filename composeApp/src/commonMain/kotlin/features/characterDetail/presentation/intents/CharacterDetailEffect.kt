package features.characterDetail.presentation.intents

import androidx.compose.runtime.Immutable
import core.util.Reducer

@Immutable
sealed class CharacterDetailEffect: Reducer.ViewEffect {
    data class NavigateToEpisodeDetail(val id: Int): CharacterDetailEffect()

    data class NavigateToLocationDetail(val id: Int): CharacterDetailEffect()
}
