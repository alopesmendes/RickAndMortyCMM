package features.locations.domain.useCases

import core.entities.State
import core.entities.toFailure
import features.locations.domain.entities.LocationList
import features.locations.domain.repositories.LocationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLocationListUseCase(private val repository: LocationsRepository) {
    suspend operator fun invoke(page: String? = null): Flow<State<LocationList>> = flow {
        try {
            emit(State.Loading)
            val result = repository.getLocationList(page)
            val state = result.fold(
                onSuccess = { State.Success(it) },
                onFailure = { State.Error(it.toFailure()) }
            )
            emit(state)
        } catch (e: Exception) {
            emit(State.Error(e.toFailure()))
        }

    }

}