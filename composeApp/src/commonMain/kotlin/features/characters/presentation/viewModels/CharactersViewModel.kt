package features.characters.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.entities.State
import features.characters.domain.entities.CharacterList
import features.characters.domain.useCases.GetCharactersListUseCase
import features.characters.presentation.intents.CharactersIntent
import features.characters.presentation.state.CharactersListState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ViewModel() {
    private val _characterListState: MutableStateFlow<CharactersListState> = MutableStateFlow(CharactersListState())
    val characterListState = _characterListState.asStateFlow()

    init {
        onHandle(CharactersIntent.FetchCharacters())
    }

    fun onHandle(intent: CharactersIntent) {
        viewModelScope.launch {
            when (intent) {
                is CharactersIntent.FetchCharacters -> {
                    getCharactersListUseCase(intent.page).collectLatest {
                        handleState(intent.page, it)
                    }
                }
            }
        }
    }

    private fun handleState(page: Int, state: State<CharacterList>) {
        when (state) {
            is State.Loading -> {
                _characterListState.update {
                    it.copy(isLoading = true)
                }
            }

            is State.Error -> {
                _characterListState.update {
                    it.copy(isLoading = false, error = "error")
                }
            }

            is State.Success -> {
                _characterListState.update {
                    it.copy(
                        isLoading = false,
                        characters = persistentListOf(*state.data.results.toTypedArray()),
                        page = page
                    )
                }
            }
        }
    }

}