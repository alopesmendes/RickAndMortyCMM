package features.locations.data.repositories

import features.locations.data.datasource.LocationsRemoteDatasource
import features.locations.domain.entities.LocationList
import features.locations.domain.repositories.LocationsRepository
import features.locations.mapper.mapTo

class LocationsRepositoryImpl(
    private val locationsRemoteDatasource: LocationsRemoteDatasource
): LocationsRepository {
    override suspend fun getLocationList(page: String?): Result<LocationList> {
        return try {
            val response = locationsRemoteDatasource.getLocationListDto(page)
            Result.success(response.mapTo())
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}