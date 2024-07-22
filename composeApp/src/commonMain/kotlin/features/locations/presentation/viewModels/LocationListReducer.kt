package features.locations.presentation.viewModels

import core.util.Reducer
import features.locations.domain.useCases.GetLocationListUseCase
import features.locations.mapper.mapTo
import features.locations.presentation.intents.LocationListEffect
import features.locations.presentation.intents.LocationListIntent
import features.locations.presentation.state.LocationListState
import kotlinx.coroutines.flow.collectLatest
import kotlin.reflect.KFunction1

class LocationListReducer(
    private val getLocationListUseCase: GetLocationListUseCase
): Reducer<LocationListState, LocationListIntent, LocationListEffect> {
    override suspend fun reduce(
        updateState: KFunction1<(LocationListState) -> LocationListState, Unit>,
        intent: LocationListIntent
    ) {
        when(intent) {
            is LocationListIntent.FetchLocations -> {
                if (!intent.hasMore) {
                    return
                }
                getLocationListUseCase(intent.page).collectLatest { state ->
                    updateState.invoke(state::mapTo)
                }
            }
        }
    }
}