package features.locationDetail.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.components.InfiniteLazyColumn
import core.components.TitleComponent
import features.locationDetail.presentation.state.LocationDetailItem
import features.locationDetail.presentation.state.ResidentItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LocationDetailView(
    modifier: Modifier = Modifier,
    isResidentsLoading: Boolean = false,
    locationDetail: LocationDetailItem,
    residents: ImmutableList<ResidentItem>,
    onResidentClick: (Int) -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        TitleComponent(
            modifier = Modifier.fillMaxWidth(),
            title = locationDetail.name
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            elevation = 4.dp,
        ) {
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = locationDetail.type,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.weight(1f)
                )
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )
                Text(
                    text = locationDetail.dimension,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.weight(1f)
                )
            }
        }


        InfiniteLazyColumn(
            modifier = Modifier.fillMaxWidth(),
            loadingItem = {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            },
            loading = isResidentsLoading,
            items = residents,
            itemKey = { it.id },
            itemContent = {
                LocationDetailResidentListItem(
                    modifier = Modifier.fillMaxWidth(),
                    resident = it,
                    onClick = { onResidentClick(it.id) }
                )
            }
        )
    }
}

@Preview
@Composable
fun LocationDetailViewPreview() {
    LocationDetailView(
        locationDetail = LocationDetailItem(
            id = 8282,
            name = "Earth",
            type = "decore",
            dimension = "est",
            residents = listOf()
        ),
        residents = persistentListOf(
            ResidentItem(
                id = 8282,
                name = "Adolph Franks",
                image = "https://rickandmortyapi.com/api/character/avatar/8282.jpeg"
            ),
            ResidentItem(
                id = 8283,
                name = "Atom Franks",
                image = "https://rickandmortyapi.com/api/character/avatar/8283.jpeg"
            ),
        )
    )
}