package features.locationDetail.domain.repositories

import features.locationDetail.domain.entities.LocationDetail
import features.locationDetail.domain.entities.Resident

interface LocationDetailRepository {
    /**
     * Get location detail
     *
     * @param id the location id
     * @return [Result] of [LocationDetail]
     */
    suspend fun getLocationDetail(id: Int): Result<LocationDetail>

    /**
     * Get residents
     *
     * @param residents the list of resident ids
     * @return [Result] of [List] of [Resident]
     */
    suspend fun getResidents(residents: List<String>): Result<List<Resident>>
}