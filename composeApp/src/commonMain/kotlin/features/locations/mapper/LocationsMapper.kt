package features.locations.mapper

import core.mapper.mapTo
import features.locations.data.models.LocationDto
import features.locations.data.models.LocationListDto
import features.locations.domain.entities.Location
import features.locations.domain.entities.LocationList

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