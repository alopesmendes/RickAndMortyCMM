package features.episodes.data.datasource

import features.episodes.data.models.EpisodeListDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class EpisodesRemoteDatasourceImpl(
    private val httpClient: HttpClient
): EpisodesRemoteDatasource {
    override suspend fun getEpisodeList(page: String?): EpisodeListDto {
        val url = page ?: "https://rickandmortyapi.com/api/episode"
        val httpResponse = httpClient.get(urlString = url)
        return httpResponse.body<EpisodeListDto>()
    }
}