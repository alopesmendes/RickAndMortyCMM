package features.locationDetail.presentation.viewModels

import core.entities.State
import core.util.Reducer
import features.locationDetail.domain.useCases.GetLocationDetailUseCase
import features.locationDetail.domain.useCases.GetResidentsUseCase
import features.locationDetail.mapper.mapTo
import features.locationDetail.presentation.intents.LocationDetailEffect
import features.locationDetail.presentation.intents.LocationDetailIntent
import features.locationDetail.presentation.state.LocationDetailState
import kotlinx.coroutines.flow.collectLatest
import kotlin.reflect.KFunction1

class LocationDetailReducer(
    private val getLocationDetailUseCase: GetLocationDetailUseCase,
    private val getResidentsUseCase: GetResidentsUseCase,
) : Reducer<LocationDetailState, LocationDetailIntent, LocationDetailEffect> {
    override suspend fun reduce(
        updateState: KFunction1<(LocationDetailState) -> LocationDetailState, Unit>,
        intent: LocationDetailIntent,
        sendIntent: (LocationDetailIntent) -> Unit
    ) {
        when(intent) {
            is LocationDetailIntent.FetchLocationDetail -> {
                getLocationDetailUseCase(intent.id).collectLatest { state ->
                    if (state is State.Success) {
                        sendIntent(LocationDetailIntent.FetchResidents(state.data.residents))
                    }
                    updateState.invoke(state::mapTo)
                }
            }
            is LocationDetailIntent.FetchResidents -> {
                getResidentsUseCase(intent.residents).collectLatest { state ->
                    updateState.invoke(state::mapTo)
                }
            }
        }
    }
}