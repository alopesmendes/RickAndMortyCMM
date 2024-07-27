package features.locationDetail.mapper

import core.entities.State
import features.locationDetail.data.models.LocationDetailCharacterDto
import features.locationDetail.data.models.LocationDetailDto
import features.locationDetail.domain.entities.LocationDetail
import features.locationDetail.domain.entities.Resident
import features.locationDetail.presentation.state.LocationDetailItem
import features.locationDetail.presentation.state.LocationDetailState
import features.locationDetail.presentation.state.ResidentItem
import kotlinx.collections.immutable.toPersistentList
import kotlin.jvm.JvmName

fun LocationDetailDto.mapTo(): LocationDetail = LocationDetail(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    residents = residents,
    url = url,
)

fun LocationDetailCharacterDto.mapTo(): Resident = Resident(
    id = id,
    name = name,
    image = image,
)

fun List<LocationDetailCharacterDto>.mapTo(): List<Resident> = map { it.mapTo() }

fun LocationDetail.mapTo(): LocationDetailItem = LocationDetailItem(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    residents = residents,
)

fun Resident.mapTo(): ResidentItem = ResidentItem(
    id = id,
    name = name,
    image = image,
)

@JvmName("mapToLocationDetailState")
fun State<LocationDetail>.mapTo(locationDetailState: LocationDetailState): LocationDetailState {
    return when (this) {
        State.Loading -> locationDetailState.copy(isLoading = true)
        is State.Error -> locationDetailState.copy(
            error = "error",
            isLoading = false,
        )
        is State.Success -> locationDetailState.copy(
            isLoading = false,
            locationDetail = data.mapTo(),
        )
    }
}

fun State<List<Resident>>.mapTo(locationDetailState: LocationDetailState): LocationDetailState {
    return when(this) {
        is State.Error -> locationDetailState.copy(
            error = "error",
            isResidentsLoading = false,
        )
        State.Loading -> locationDetailState.copy(isResidentsLoading = true)
        is State.Success -> locationDetailState.copy(
            isResidentsLoading = false,
            residents = data.map { it.mapTo() }.toPersistentList(),
        )
    }
}

