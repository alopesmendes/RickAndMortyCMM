package features.locations.presentation.state

import androidx.compose.runtime.Immutable

@Immutable
data class LocationItem(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
)
