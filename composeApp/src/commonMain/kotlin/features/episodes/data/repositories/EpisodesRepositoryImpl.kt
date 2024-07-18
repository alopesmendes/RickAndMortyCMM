package features.episodes.data.repositories

import features.episodes.domain.entities.EpisodeList
import features.episodes.domain.repositories.EpisodesRepository

class EpisodesRepositoryImpl: EpisodesRepository {
    override suspend fun getEpisodeList(page: Int): Result<EpisodeList> {
        TODO("Not yet implemented")
    }
}