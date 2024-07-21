package features.characters.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import features.characters.presentation.state.CharacterItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CharactersListView(
    modifier: Modifier = Modifier,
    characters: List<CharacterItem>,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(characters, key = { it.id }) {
            CharacterCard(
                modifier = Modifier.fillMaxWidth(),
                characterItem = it,
                onClick = {},
            )
        }
    }
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
        characters = characters,
    )
}