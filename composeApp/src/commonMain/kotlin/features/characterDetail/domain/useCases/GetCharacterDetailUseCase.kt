package features.characterDetail.domain.useCases

import features.characterDetail.domain.repositories.CharacterDetailRepository

class GetCharacterDetailUseCase(
    private val repository: CharacterDetailRepository
) {
}