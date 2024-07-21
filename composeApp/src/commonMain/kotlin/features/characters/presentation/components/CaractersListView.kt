package features.characters.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.components.InfiniteLazyColumn
import features.characters.presentation.state.CharacterItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CharactersListView(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    characters: ImmutableList<CharacterItem>,
    loadMore: () -> Unit,
    onClick: (CharacterItem) -> Unit,
) {
    InfiniteLazyColumn(
        modifier = modifier,
        items = characters,
        itemKey = { it.id },
        loadMore = loadMore,
        loading = loading,
        loadingItem = {
            CircularProgressIndicator()
        },
        itemContent = {
            CharacterCard(
                modifier = Modifier.fillMaxWidth(),
                characterItem = it,
                onClick = { onClick(it) },
            )
        }
    )
}

@Composable
@Preview
fun CharactersListViewPreview() {
    val characters = List(10) {
        CharacterItem(
            id = it,
            name = "Character $it",
            image = "https://example.com/image$it.jpg",
            gender = "Gender $it",
        )
    }
    CharactersListView(
        characters = characters.toImmutableList(),
        loadMore = {},
        loading = false,
        modifier = Modifier.fillMaxSize(),
        onClick = {}
    )
}