package features.episodes.data.datasource

import features.episodes.data.models.EpisodeListDto

interface EpisodesRemoteDatasource {
    suspend fun getEpisodeList(page: String? = null): EpisodeListDto
}