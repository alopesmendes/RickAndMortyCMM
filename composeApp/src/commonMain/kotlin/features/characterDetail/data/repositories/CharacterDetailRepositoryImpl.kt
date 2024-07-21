package features.characterDetail.data.repositories

import features.characterDetail.data.datasource.CharacterDetailRemoteDatasource
import features.characterDetail.domain.entities.CharacterDetail
import features.characterDetail.domain.repositories.CharacterDetailRepository
import features.characterDetail.mapper.mapTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailRepositoryImpl(
    private val characterDetailRemoteDatasource: CharacterDetailRemoteDatasource
): CharacterDetailRepository {
    override suspend fun getCharacterDetail(id: Int): Result<CharacterDetail> {
        return try {
            val response = characterDetailRemoteDatasource.getCharacterDetail(id)
            Result.success(response.mapTo())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
