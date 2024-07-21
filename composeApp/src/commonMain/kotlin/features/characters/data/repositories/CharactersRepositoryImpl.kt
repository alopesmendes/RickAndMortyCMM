package features.characters.data.repositories

import features.characters.data.datasource.CharactersRemoteDatasource
import features.characters.domain.entities.CharacterList
import features.characters.domain.repositories.CharactersRepository
import features.characters.mapper.mapTo

class CharactersRepositoryImpl(
    private val charactersRemoteDatasource: CharactersRemoteDatasource
): CharactersRepository {
    override suspend fun getCharacters(page: String?): Result<CharacterList> {
        return try {
            val response = charactersRemoteDatasource.getCharacterList(page)
            Result.success(response.mapTo())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}