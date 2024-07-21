package features.characters.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
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
            modifier = Modifier.fillMaxSize().height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                model = characterItem.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.weight(1f),
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    characterItem.name,
                    style = MaterialTheme.typography.h4,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                )

                Text(
                    characterItem.gender,
                    style = MaterialTheme.typography.h5,
                )
            }
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