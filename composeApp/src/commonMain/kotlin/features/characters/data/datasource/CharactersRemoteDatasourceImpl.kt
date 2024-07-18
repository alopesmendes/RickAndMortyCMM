package features.characters.data.datasource

import features.characters.data.models.CharacterListDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CharactersRemoteDatasourceImpl(
    private val httpClient: HttpClient
): CharactersRemoteDatasource {
    override suspend fun getCharacterList(page: Int): CharacterListDto {
        val httpResponse = httpClient.get(urlString = "https://rickandmortyapi.com/api/character?page=$page")
        return httpResponse.body<CharacterListDto>()
    }
}