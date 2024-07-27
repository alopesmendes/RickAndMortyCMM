package features.locationDetail.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import features.locationDetail.presentation.state.LocationDetailState

@Composable
fun LocationDetailContent(
    modifier: Modifier = Modifier,
    locationDetailState: LocationDetailState,
    onResidentClick: (Int) -> Unit = {},
) {
    when {
        locationDetailState.error != null -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = locationDetailState.error, color = MaterialTheme.colors.error)
            }
        }

        else -> {
            LocationDetailView(
                modifier = modifier.padding(8.dp),
                locationDetail = locationDetailState.locationDetail,
                residents = locationDetailState.residents,
                isResidentsLoading = locationDetailState.isResidentsLoading,
                onResidentClick = onResidentClick,
            )
        }
    }
}