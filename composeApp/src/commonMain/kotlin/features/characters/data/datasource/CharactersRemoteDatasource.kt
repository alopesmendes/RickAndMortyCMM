package features.characters.data.datasource

import features.characters.data.models.CharacterListDto

interface CharactersRemoteDatasource {
    suspend fun getCharacterList(page: Int = 1): CharacterListDto
}