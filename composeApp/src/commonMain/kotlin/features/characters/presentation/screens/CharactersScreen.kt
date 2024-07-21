package features.characters.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import features.characters.presentation.components.CharactersListView
import features.characters.presentation.intents.CharacterListEffect
import features.characters.presentation.state.CharactersListState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CharactersScreen(
    charactersListState: CharactersListState,
    loadMore: () -> Unit,
    onClick: (Int) -> Unit
) {
    when {
        charactersListState.error != null -> {
            Text(charactersListState.error)
        }
        else -> {
           CharactersListView(
               modifier = Modifier.fillMaxSize(),
               characters = charactersListState.characters,
               loading = charactersListState.isLoading,
               loadMore = loadMore,
               onClick = { onClick(it.id) }
           )
        }
    }
}

@Composable
@Preview
fun CharactersScreenPreview() {
    CharactersScreen(
        charactersListState = CharactersListState(
            characters = persistentListOf(),
            isLoading = false,
        ),
        onClick = {},
        loadMore = {}
    )
}