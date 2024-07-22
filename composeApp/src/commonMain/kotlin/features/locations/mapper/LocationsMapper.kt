package features.locations.mapper

import core.entities.State
import core.mapper.mapTo
import features.locations.data.models.LocationDto
import features.locations.data.models.LocationListDto
import features.locations.domain.entities.Location
import features.locations.domain.entities.LocationList
import features.locations.presentation.state.LocationItem
import features.locations.presentation.state.LocationListState
import kotlinx.collections.immutable.persistentListOf

fun LocationListDto.mapTo(): LocationList = LocationList(
    info = info.mapTo(),
    results = results.mapTo(),
)

fun LocationDto.mapTo(): Location = Location(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    url = url,
)

fun List<LocationDto>.mapTo(): List<Location> = map { it.mapTo() }

fun Location.mapTo(): LocationItem = LocationItem(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
)

fun State<LocationList>.mapTo(
    locationListState: LocationListState
): LocationListState {
    return when(this) {
        is State.Success -> locationListState.copy(
            info = data.info.mapTo(),
            locations = persistentListOf(
                *locationListState.locations.toTypedArray(),
                *data.results.map { it.mapTo() }.toTypedArray(),
            ),
            isLoading = false,
        )

        is State.Error -> locationListState.copy(
            isLoading = false,
            error = "Error"
        )
        State.Loading -> locationListState.copy(
            isLoading = true
        )
    }
}