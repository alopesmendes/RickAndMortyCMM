package features.characterDetail.data.datasource

import features.characterDetail.data.models.CharacterDetailDto

interface CharacterDetailRemoteDatasource {
    suspend fun getCharacterDetail(id: Int): CharacterDetailDto
}