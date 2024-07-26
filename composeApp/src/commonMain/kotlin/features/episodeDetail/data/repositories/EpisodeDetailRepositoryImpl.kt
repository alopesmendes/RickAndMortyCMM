package features.episodeDetail.data.repositories

import features.episodeDetail.data.datasource.EpisodeDetailRemoteDatasource
import features.episodeDetail.domain.entities.EpisodeCharacter
import features.episodeDetail.domain.entities.EpisodeDetail
import features.episodeDetail.domain.repositories.EpisodeDetailRepository
import features.episodeDetail.mapper.mapTo

class EpisodeDetailRepositoryImpl(
    private val episodeDetailRemoteDatasource: EpisodeDetailRemoteDatasource,
): EpisodeDetailRepository {
    override suspend fun getEpisodeDetail(id: Int): Result<EpisodeDetail> {
        return try {
            val response = episodeDetailRemoteDatasource.getEpisodeDetail(id)
            Result.success(response.mapTo())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCharacters(characters: List<String>): Result<List<EpisodeCharacter>> {
        return try {
            val response = episodeDetailRemoteDatasource.getCharacters(characters)
            Result.success(response.mapTo())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
