package features.locationDetail.presentation.state

import androidx.compose.runtime.Immutable

@Immutable
data class ResidentItem(
    val id: Int,
    val name: String,
    val image: String,
)
