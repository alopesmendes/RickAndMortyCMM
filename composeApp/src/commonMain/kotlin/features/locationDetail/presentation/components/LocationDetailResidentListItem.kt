package features.locationDetail.presentation.components

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
import features.locationDetail.presentation.state.ResidentItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LocationDetailResidentListItem(
    modifier: Modifier = Modifier,
    resident: ResidentItem,
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
            model = resident.image,
            contentDescription = resident.name,
            contentScale = ContentScale.Crop
        )

        Text(
            text = resident.name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.weight(1f),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}

@Preview
@Composable
fun LocationDetailResidentListItemPreview() {
    LocationDetailResidentListItem(
        resident = ResidentItem(
            id = 1,
            name = "Rick Sanchez",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        )
    )
}