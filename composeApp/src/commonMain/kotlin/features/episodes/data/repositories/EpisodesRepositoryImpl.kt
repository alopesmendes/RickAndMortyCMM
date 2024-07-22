package features.episodes.data.repositories

import features.episodes.data.datasource.EpisodesRemoteDatasource
import features.episodes.domain.entities.EpisodeList
import features.episodes.domain.repositories.EpisodesRepository
import features.episodes.mapper.mapTo

class EpisodesRepositoryImpl(
    private val episodesRemoteDatasource: EpisodesRemoteDatasource,
): EpisodesRepository {
    override suspend fun getEpisodeList(page: String?): Result<EpisodeList> {
        return try {
            val response = episodesRemoteDatasource.getEpisodeList(page)
            Result.success(response.mapTo())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}