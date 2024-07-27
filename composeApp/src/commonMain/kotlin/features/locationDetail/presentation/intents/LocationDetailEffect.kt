package features.locationDetail.presentation.intents

import androidx.compose.runtime.Immutable
import core.util.Reducer

@Immutable
sealed interface LocationDetailEffect: Reducer.ViewEffect {
    data class NavigateToResident(val id: Int): LocationDetailEffect
}