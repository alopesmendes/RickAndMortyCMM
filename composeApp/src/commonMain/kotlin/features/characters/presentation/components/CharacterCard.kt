package features.characters.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Transgender
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
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
        modifier = modifier.padding(horizontal = 4.dp),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = characterItem.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colors.primarySurface, CircleShape)
                    .clip(CircleShape),
            )
            Column(
                modifier = Modifier.fillMaxHeight().weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Text(
                        "#${characterItem.id}",
                        style = MaterialTheme.typography.h5,
                        fontFamily = FontFamily.Cursive
                    )
                    Text(
                        characterItem.name,
                        style = MaterialTheme.typography.h5,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        Icons.Filled.Transgender,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primarySurface,
                    )
                    Text(
                        characterItem.gender,
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.primarySurface,
                    )
                }
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