package features.characters.domain.repositories

import features.characters.domain.entities.CharacterList

interface CharactersRepository {
    /**
     * Get characters
     *
     * @param page the current page
     * @return [Result] of [CharacterList]
     */
    suspend fun getCharacters(page: String? = null): Result<CharacterList>
}