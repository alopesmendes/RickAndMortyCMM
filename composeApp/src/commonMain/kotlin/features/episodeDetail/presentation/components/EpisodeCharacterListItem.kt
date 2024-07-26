package features.episodeDetail.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import features.episodeDetail.presentation.state.EpisodeCharacterItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EpisodeCharacterListItem(
    modifier: Modifier = Modifier,
    episodeCharacter: EpisodeCharacterItem,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier.clip(CircleShape),
            model = episodeCharacter.image,
            contentDescription = episodeCharacter.name,
            contentScale = ContentScale.Crop
        )

        Text(
            text = episodeCharacter.name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.weight(1f),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}

@Preview
@Composable
fun EpisodeCharacterListItemPreview() {
    EpisodeCharacterListItem(
        episodeCharacter = EpisodeCharacterItem(
            id = 6990,
            name = "Gilda Sanders",
            image = "https://rickandmortyapi.com/api/character/avatar/6990.jpeg"
        )
    )
}