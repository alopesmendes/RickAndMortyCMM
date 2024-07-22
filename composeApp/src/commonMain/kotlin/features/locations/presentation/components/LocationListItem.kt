package features.locations.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import features.locations.presentation.state.LocationItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LocationListItem(
    modifier: Modifier = Modifier,
    location: LocationItem,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.padding(horizontal = 4.dp),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(
                vertical = 4.dp,
                horizontal = 8.dp,
            ),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "#${location.id}",
                        style = MaterialTheme.typography.h5,
                        fontFamily = FontFamily.Cursive,
                    )
                    Text(
                        text = location.name,
                        style = MaterialTheme.typography.h5,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = location.type,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.primaryVariant,
                    )
                    Text(
                        text = location.dimension,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.primaryVariant,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LocationListItemPreview() {
    LocationListItem(
        location = LocationItem(
            id = 1382, name = "Colin Scott", type = "quem", dimension = "causae"
        ),
        onClick = {}
    )
}