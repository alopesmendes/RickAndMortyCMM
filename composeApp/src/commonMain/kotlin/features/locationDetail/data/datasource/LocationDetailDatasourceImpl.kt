package features.locationDetail.data.datasource

import features.locationDetail.data.models.LocationDetailCharacterDto
import features.locationDetail.data.models.LocationDetailDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class LocationDetailDatasourceImpl(
    private val httpClient: HttpClient
) : LocationDetailDatasource {
    override suspend fun getLocationDetail(id: Int): LocationDetailDto {
        val response = httpClient.get("https://rickandmortyapi.com/api/location/$id")
        return response.body<LocationDetailDto>()
    }

    private suspend fun getCharacter(characterUrl: String): LocationDetailCharacterDto {
        val response = httpClient.get(characterUrl)
        return response.body<LocationDetailCharacterDto>()
    }

    override suspend fun getCharacters(characters: List<String>): List<LocationDetailCharacterDto> {
        return characters.map { getCharacter(it) }
    }
}