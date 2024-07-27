package features.locationDetail.presentation.viewModels

import androidx.lifecycle.SavedStateHandle
import core.util.BaseViewModel
import core.util.Constants
import features.locationDetail.domain.useCases.GetLocationDetailUseCase
import features.locationDetail.domain.useCases.GetResidentsUseCase
import features.locationDetail.presentation.intents.LocationDetailEffect
import features.locationDetail.presentation.intents.LocationDetailIntent
import features.locationDetail.presentation.state.LocationDetailState

class LocationDetailViewModel(
    savedStateHandle: SavedStateHandle,
    getLocationDetailUseCase: GetLocationDetailUseCase,
    getResidentsUseCase: GetResidentsUseCase,
): BaseViewModel<LocationDetailState, LocationDetailIntent, LocationDetailEffect>(
    initialState = LocationDetailState(),
    reducer = LocationDetailReducer(
        getLocationDetailUseCase = getLocationDetailUseCase,
        getResidentsUseCase = getResidentsUseCase,
    )
) {
    private val locationDetailId = savedStateHandle.get<Int>(Constants.PARAM_LOCATION_DETAIL)

    init {
        locationDetailId?.let {
            sendIntent(LocationDetailIntent.FetchLocationDetail(it))
        }
    }
}