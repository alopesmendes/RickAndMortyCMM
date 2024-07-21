package features.locationDetail.domain.useCases

import core.entities.State
import features.locationDetail.domain.entities.LocationDetailWithResidents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLocationDetailWithResidentsUseCase(
    private val getLocationDetailUseCase: GetLocationDetailUseCase,
    private val getResidentsUseCase: GetResidentsUseCase,
) {
    operator fun invoke(locationId: Int): Flow<State<LocationDetailWithResidents>> = flow {

    }
}