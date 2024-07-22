package features.locations.presentation.intents

import androidx.compose.runtime.Immutable
import core.util.Reducer

@Immutable
sealed interface LocationListEffect: Reducer.ViewEffect {
    data class NavigationToLocationDetail(val id: Int): LocationListEffect
}