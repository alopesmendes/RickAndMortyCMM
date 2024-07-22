package features.locations.domain.repositories

import features.locations.domain.entities.LocationList

interface LocationsRepository {
    /**
     * Get location list
     *
     * @param page the current page
     * @return [Result] of [LocationList]
     */
    suspend fun getLocationList(page: String? = null): Result<LocationList>
}