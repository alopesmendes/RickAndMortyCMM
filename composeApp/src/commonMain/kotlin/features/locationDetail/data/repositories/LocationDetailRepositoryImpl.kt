package features.locationDetail.data.repositories

import features.locationDetail.data.datasource.LocationDetailDatasource
import features.locationDetail.domain.entities.LocationDetail
import features.locationDetail.domain.entities.Resident
import features.locationDetail.domain.repositories.LocationDetailRepository
import features.locationDetail.mapper.mapTo

class LocationDetailRepositoryImpl(
    private val locationDetailDatasource: LocationDetailDatasource
): LocationDetailRepository {
    override suspend fun getLocationDetail(id: Int): Result<LocationDetail> {
        return try {
            val response = locationDetailDatasource.getLocationDetail(id)
            Result.success(response.mapTo())
        } catch (e: Exception) {
           Result.failure(e)
        }
    }

    override suspend fun getResidents(residents: List<String>): Result<List<Resident>> {
        return try {
            val response = locationDetailDatasource.getCharacters(residents)
            Result.success(response.mapTo())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}