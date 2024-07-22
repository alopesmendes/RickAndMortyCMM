package features.characterDetail.data.datasource

import features.characterDetail.data.models.CharacterDetailDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CharacterDetailRemoteDatasourceImpl(
    private val httpClient: HttpClient
): CharacterDetailRemoteDatasource {
    override suspend fun getCharacterDetail(id: Int): CharacterDetailDto {
        val httpResponse = httpClient.get(urlString = "https://rickandmortyapi.com/api/character/$id")
        return httpResponse.body<CharacterDetailDto>()
    }
}