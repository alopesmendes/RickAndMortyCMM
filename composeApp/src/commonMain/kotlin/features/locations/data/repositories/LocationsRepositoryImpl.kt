package features.locations.data.repositories

import features.locations.domain.entities.LocationList
import features.locations.domain.repositories.LocationsRepository

class LocationsRepositoryImpl: LocationsRepository {
    override suspend fun getLocationList(page: Int): Result<LocationList> {
        TODO("Not yet implemented")
    }
}