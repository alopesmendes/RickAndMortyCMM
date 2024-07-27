package features.locationDetail.presentation.intents

import androidx.compose.runtime.Immutable
import core.util.Reducer

@Immutable
sealed interface LocationDetailIntent: Reducer.ViewIntent {
    data class FetchLocationDetail(val id: Int): LocationDetailIntent

    data class FetchResidents(val residents: List<String>): LocationDetailIntent
}