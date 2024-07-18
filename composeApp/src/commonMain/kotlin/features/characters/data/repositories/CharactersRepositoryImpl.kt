package features.characters.data.repositories

import features.characters.data.datasource.CharactersRemoteDatasource
import features.characters.domain.entities.CharacterList
import features.characters.domain.repositories.CharactersRepository

class CharactersRepositoryImpl(
    private val charactersRemoteDatasource: CharactersRemoteDatasource
): CharactersRepository {
    override suspend fun getCharacters(page: Int): Result<CharacterList> {
        TODO("Not yet implemented")
    }
}