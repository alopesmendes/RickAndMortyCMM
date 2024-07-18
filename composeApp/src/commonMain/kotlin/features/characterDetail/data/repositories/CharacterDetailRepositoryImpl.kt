package features.characterDetail.data.repositories

import features.characterDetail.domain.entities.CharacterDetail
import features.characterDetail.domain.repositories.CharacterDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailRepositoryImpl: CharacterDetailRepository {
    override suspend fun getCharacterDetail(id: Int): Result<CharacterDetail> {
        TODO("Not yet implemented")
    }
}
