package features.characters.presentation.intents

sealed interface CharactersIntent {
    data class FetchCharacters(val page: Int = 1) : CharactersIntent
}