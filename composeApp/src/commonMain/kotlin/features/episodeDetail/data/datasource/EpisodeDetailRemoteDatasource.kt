package features.episodeDetail.data.datasource

import features.episodeDetail.data.models.EpisodeDetailDto

interface EpisodeDetailRemoteDatasource {
    suspend fun getEpisodeDetail(id: Int): EpisodeDetailDto
}