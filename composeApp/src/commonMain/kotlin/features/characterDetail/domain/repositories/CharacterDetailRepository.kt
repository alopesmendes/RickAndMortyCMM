package features.characterDetail.domain.repositories

import features.characterDetail.domain.entities.CharacterDetail

interface CharacterDetailRepository {
    /**
     * Get character detail
     *
     * @param id the character id
     * @return [Result] of [CharacterDetail]
     */
    suspend fun getCharacterDetail(id: Int): Result<CharacterDetail>
}