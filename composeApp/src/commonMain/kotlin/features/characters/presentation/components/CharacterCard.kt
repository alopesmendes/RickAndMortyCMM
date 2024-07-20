package features.characters.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import features.characters.presentation.state.CharacterItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    characterItem: CharacterItem,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AsyncImage(
                model = characterItem.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        Column {

            Text(characterItem.name)
        }
    }
}

@Preview
@Composable
fun CharacterCardPreview() {
    val characterItem = CharacterItem(
        id = 1,
        name = "rick",
        image = "",
        gender = "male"
    )
    CharacterCard(characterItem = characterItem, onClick = {})

}