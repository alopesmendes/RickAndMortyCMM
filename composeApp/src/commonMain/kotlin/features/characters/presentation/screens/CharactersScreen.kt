package features.characters.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import features.characters.presentation.components.CharactersListView
import features.characters.presentation.state.CharactersListState
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CharactersScreen(
    charactersListState: CharactersListState
) {
    when {
        charactersListState.isLoading -> {
            CircularProgressIndicator()
        }
        charactersListState.error != null -> {
            Text(charactersListState.error)
        }
        else -> {
           CharactersListView(
               modifier = Modifier.fillMaxSize(),
               characters = charactersListState.characters
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
        )
    )
}