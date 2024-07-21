package features.characterDetail.domain.useCases

import core.entities.State
import core.entities.toFailure
import features.characterDetail.domain.entities.CharacterDetail
import features.characterDetail.domain.repositories.CharacterDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCharacterDetailUseCase(
    private val repository: CharacterDetailRepository
) {
    operator fun invoke(id: Int): Flow<State<CharacterDetail>> = flow {
        try {
            emit(State.Loading)
            val result = repository.getCharacterDetail(id)
            val state = result.fold(
                onSuccess = {
                    State.Success(it)
                },
                onFailure = {
                    State.Error(it.toFailure())
                }
            )
            emit(state)
        } catch (e: Exception) {
            emit(State.Error(e.toFailure()))
        }
    }
}