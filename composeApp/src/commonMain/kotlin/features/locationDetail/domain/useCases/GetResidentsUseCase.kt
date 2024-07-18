package features.locationDetail.domain.useCases

import core.entities.State
import core.entities.toFailure
import features.locationDetail.domain.entities.Resident
import features.locationDetail.domain.repositories.LocationDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetResidentsUseCase(
    private val locationDetailRepository: LocationDetailRepository
) {
    operator fun invoke(residentIds: List<Int>): Flow<State<List<Resident>>> = flow {
        emit(State.Loading)

        val result = locationDetailRepository.getResidents(residentIds)
        val state = result.fold(
            onSuccess = { State.Success(it) },
            onFailure = { State.Error(it.toFailure()) },
        )
        emit(state)
    }

}