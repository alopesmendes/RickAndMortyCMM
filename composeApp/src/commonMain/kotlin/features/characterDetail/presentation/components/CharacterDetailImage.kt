package features.characterDetail.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CharacterDetailImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    Box(
        modifier = modifier
            .border(2.dp, MaterialTheme.colors.primary, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize(),
        )
    }
}

@Preview
@Composable
fun CharacterDetailImagePreview() {
    CharacterDetailImage(
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
    )
}