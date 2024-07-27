package features.locationDetail.mapper

import features.locationDetail.data.models.LocationDetailCharacterDto
import features.locationDetail.data.models.LocationDetailDto
import features.locationDetail.domain.entities.LocationDetail
import features.locationDetail.domain.entities.Resident

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