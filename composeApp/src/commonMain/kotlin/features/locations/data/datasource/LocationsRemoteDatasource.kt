package features.locations.data.datasource

import features.locations.data.models.LocationListDto

interface LocationsRemoteDatasource {
    suspend fun getLocationListDto(page: String? = null): LocationListDto
}