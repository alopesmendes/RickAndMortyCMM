package features.locationDetail.domain.useCases

import core.entities.State
import core.entities.map
import core.util.Tools.extractIdFromUrl
import features.locationDetail.domain.entities.LocationDetail
import features.locationDetail.domain.entities.LocationDetailWithResidents
import features.locationDetail.domain.entities.Resident
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class GetLocationDetailWithResidentsUseCase(
    private val getLocationDetailUseCase: GetLocationDetailUseCase,
    private val getResidentsUseCase: GetResidentsUseCase,
) {
    operator fun invoke(locationId: Int): Flow<State<LocationDetailWithResidents>> = flow {

    }
}