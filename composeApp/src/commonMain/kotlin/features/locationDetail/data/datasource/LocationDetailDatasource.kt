package features.locationDetail.data.datasource

import features.locationDetail.data.models.LocationDetailCharacterDto
import features.locationDetail.data.models.LocationDetailDto

interface LocationDetailDatasource {
    suspend fun getLocationDetail(id: Int): LocationDetailDto

    suspend fun getCharacters(characters: List<String>): List<LocationDetailCharacterDto>
}