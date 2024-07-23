package features.episodeDetail.data.datasource

import features.characters.data.models.CharacterDto
import features.episodeDetail.data.models.EpisodeDetailCharacterDto
import features.episodeDetail.data.models.EpisodeDetailDto

interface EpisodeDetailRemoteDatasource {
    suspend fun getEpisodeDetail(id: Int): EpisodeDetailDto

    suspend fun getCharacters(characters: List<String>): List<EpisodeDetailCharacterDto>
}