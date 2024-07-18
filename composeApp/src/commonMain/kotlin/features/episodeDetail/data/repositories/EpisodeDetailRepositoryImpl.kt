package features.episodeDetail.data.repositories

import features.episodeDetail.domain.entities.EpisodeDetail
import features.episodeDetail.domain.repositories.EpisodeDetailRepository

class EpisodeDetailRepositoryImpl: EpisodeDetailRepository {
    override suspend fun getEpisodeDetail(id: Int): Result<EpisodeDetail> {
        TODO("Not yet implemented")
    }
}
