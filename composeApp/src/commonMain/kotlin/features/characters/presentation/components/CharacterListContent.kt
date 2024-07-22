package features.characters.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import features.characters.presentation.state.CharactersListState

@Composable
fun CharacterListContent(
    modifier: Modifier = Modifier,
    charactersListState: CharactersListState,
    loadMore: () -> Unit,
    onClick: (Int) -> Unit
) {
    when {
        charactersListState.error != null -> {
            Text(
                charactersListState.error,
                modifier = modifier.fillMaxSize(),
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.error,
            )
        }

        else -> {
            CharactersListView(
                modifier = modifier.fillMaxSize(),
                characters = charactersListState.characters,
                loading = charactersListState.isLoading,
                loadMore = loadMore,
                onClick = { onClick(it.id) }
            )
        }
    }
}

