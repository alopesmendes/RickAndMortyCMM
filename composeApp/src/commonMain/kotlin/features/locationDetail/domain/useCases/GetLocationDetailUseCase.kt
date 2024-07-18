package features.locationDetail.domain.useCases

import core.entities.State
import core.entities.toFailure
import features.locationDetail.domain.entities.LocationDetail
import features.locationDetail.domain.repositories.LocationDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLocationDetailUseCase(
    private val repository: LocationDetailRepository
) {
    operator fun invoke(id: Int): Flow<State<LocationDetail>> = flow {
       emit(State.Loading)

        val result = repository.getLocationDetail(id)
        val state = result.fold(
            onSuccess = { State.Success(it) },
            onFailure = { State.Error(it.toFailure()) },
        )
        emit(state)
    }

}