package features.characterDetail.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.TravelExplore
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CharacterDetailInfoItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    data: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                icon,
                tint = MaterialTheme.colors.primaryVariant,
                contentDescription = null,
            )
            Text(label, style = MaterialTheme.typography.body1)
        }

        Text(
            text = data,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primarySurface
        )
    }
}

@Preview
@Composable
fun CharacterDetailInfoItemPreview() {
    CharacterDetailInfoItem(
        icon = Icons.Filled.Person,
        label = "Type",
        data = "Human"
    )
}