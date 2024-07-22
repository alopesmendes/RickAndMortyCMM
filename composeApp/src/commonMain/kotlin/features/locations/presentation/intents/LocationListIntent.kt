package features.locations.presentation.intents

import androidx.compose.runtime.Immutable
import core.util.Reducer

@Immutable
sealed interface LocationListIntent: Reducer.ViewIntent {
    data class FetchLocations(val page: String? = null, val hasMore: Boolean = true): LocationListIntent
}