package features.locations.presentation.state

import androidx.compose.runtime.Immutable
import core.entities.InfoItem
import core.util.Reducer
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class LocationListState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val info: InfoItem = InfoItem(),
    val locations: ImmutableList<LocationItem>,
): Reducer.ViewState
