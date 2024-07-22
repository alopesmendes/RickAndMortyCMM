package features.locations.presentation.viewModels

import core.util.BaseViewModel
import features.locations.domain.useCases.GetLocationListUseCase
import features.locations.presentation.intents.LocationListEffect
import features.locations.presentation.intents.LocationListIntent
import features.locations.presentation.state.LocationListState

class LocationsViewModel(
    getLocationListUseCase: GetLocationListUseCase
): BaseViewModel<LocationListState, LocationListIntent, LocationListEffect>(
    initialState = LocationListState(),
    reducer = LocationListReducer(
        getLocationListUseCase = getLocationListUseCase,
    )
) {

    init {
        sendIntent(LocationListIntent.FetchLocations())
    }
}