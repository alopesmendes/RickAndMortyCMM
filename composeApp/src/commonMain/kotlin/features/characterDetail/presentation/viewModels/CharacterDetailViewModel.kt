package features.characterDetail.presentation.viewModels

import androidx.lifecycle.ViewModel
import features.characterDetail.domain.useCases.GetCharacterDetailUseCase

class CharacterDetailViewModel(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
) : ViewModel() {

}