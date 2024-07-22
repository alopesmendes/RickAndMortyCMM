package features.episodes.domain.repositories

import features.episodes.domain.entities.EpisodeList

interface EpisodesRepository {
    /**
     * Get episode list
     *
     * @param page the current page
     * @return [Result] of [EpisodeList]
     */
    suspend fun getEpisodeList(page: String? = null): Result<EpisodeList>
}