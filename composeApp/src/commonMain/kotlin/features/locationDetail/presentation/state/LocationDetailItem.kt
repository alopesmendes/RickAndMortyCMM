package features.locationDetail.presentation.state

import androidx.compose.runtime.Immutable

@Immutable
data class LocationDetailItem(
    val id: Int = -1,
    val name: String = "",
    val type: String = "",
    val dimension: String = "",
    val residents: List<String> = emptyList(),
)
