package features.locationDetail.mapper

import features.locationDetail.data.models.LocationDetailDto
import features.locationDetail.domain.entities.LocationDetail

fun LocationDetailDto.mapTo(): LocationDetail = LocationDetail(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    residents = residents,
    url = url,
)