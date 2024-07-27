package features.locationDetail.presentation.state

import androidx.compose.runtime.Immutable
import core.util.Reducer
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class LocationDetailState(
    val isLoading: Boolean = false,
    val isResidentsLoading: Boolean = false,
    val locationDetail: LocationDetailItem = LocationDetailItem(),
    val residents: ImmutableList<ResidentItem> = persistentListOf(),
    val error: String? = null
): Reducer.ViewState
