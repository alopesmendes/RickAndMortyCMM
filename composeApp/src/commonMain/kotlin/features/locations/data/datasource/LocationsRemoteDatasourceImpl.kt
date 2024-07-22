package features.locations.data.datasource

import features.locations.data.models.LocationListDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class LocationsRemoteDatasourceImpl(
    private val httpClient: HttpClient
): LocationsRemoteDatasource {
    override suspend fun getLocationListDto(page: String?): LocationListDto {
        val url = page ?: "https://rickandmortyapi.com/api/location"
        val httpResponse = httpClient.get(url)
        return httpResponse.body<LocationListDto>()
    }
}