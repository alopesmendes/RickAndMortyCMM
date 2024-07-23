package features.episodeDetail.domain.repositories

import features.episodeDetail.domain.entities.EpisodeCharacter
import features.episodeDetail.domain.entities.EpisodeDetail

interface EpisodeDetailRepository {
    /**
     * Get episode detail
     *
     * @param id the id of the episode
     * @return [Result] of [EpisodeDetail]
     */
    suspend fun getEpisodeDetail(id: Int): Result<EpisodeDetail>

    suspend fun getCharacters(characters: List<String>): Result<List<EpisodeCharacter>>
}