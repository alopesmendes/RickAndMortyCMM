package features.locationDetail.data.repositories

import features.locationDetail.domain.entities.LocationDetail
import features.locationDetail.domain.entities.Resident
import features.locationDetail.domain.repositories.LocationDetailRepository

class LocationDetailRepositoryImpl: LocationDetailRepository {
    override suspend fun getLocationDetail(id: Int): Result<LocationDetail> {
        TODO("Not yet implemented")
    }

    override suspend fun getResidents(residentIds: List<Int>): Result<List<Resident>> {
        TODO("Not yet implemented")
    }
}