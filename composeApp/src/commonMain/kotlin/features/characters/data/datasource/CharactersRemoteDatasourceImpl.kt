package features.characters.data.datasource

import features.characters.data.models.CharacterListDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CharactersRemoteDatasourceImpl(
    private val httpClient: HttpClient
): CharactersRemoteDatasource {
    override suspend fun getCharacterList(page: String?): CharacterListDto {
        val url = page ?: "https://rickandmortyapi.com/api/character"
        val httpResponse = httpClient.get(urlString = url)
        return httpResponse.body<CharacterListDto>()
    }
}