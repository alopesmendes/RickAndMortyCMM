package features.episodeDetail.data.datasource

import features.episodeDetail.data.models.EpisodeDetailCharacterDto
import features.episodeDetail.data.models.EpisodeDetailDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class EpisodeDetailRemoteDatasourceImpl(
    private val httpClient: HttpClient,
): EpisodeDetailRemoteDatasource {
    override suspend fun getEpisodeDetail(id: Int): EpisodeDetailDto {
        val url = "https://rickandmortyapi.com/api/episode/$id"
        val httpResponse = httpClient.get(urlString = url)
        return httpResponse.body<EpisodeDetailDto>()
    }

    private suspend fun getCharacter(characterUrl: String): EpisodeDetailCharacterDto {
        val httpResponse = httpClient.get(urlString = characterUrl)
        return httpResponse.body<EpisodeDetailCharacterDto>()
    }

    override suspend fun getCharacters(characters: List<String>): List<EpisodeDetailCharacterDto> {
        return characters.map { getCharacter(it) }
    }
}