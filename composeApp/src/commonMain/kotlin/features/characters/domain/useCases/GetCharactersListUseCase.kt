package features.characters.domain.useCases

import core.entities.State
import core.entities.toFailure
import features.characters.domain.entities.CharacterList
import features.characters.domain.repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCharactersListUseCase(private val repository: CharactersRepository) {
    operator fun invoke(page: String? = null): Flow<State<CharacterList>> = flow {
        try {
            emit(State.Loading)

            val result = repository.getCharacters(page)
            val state = result.fold(
                onSuccess = { State.Success(it) },
                onFailure = { State.Error(it.toFailure()) },
            )

            emit(state)
        } catch (e: Exception) {
            emit(State.Error(e.toFailure()))
        }
    }
}