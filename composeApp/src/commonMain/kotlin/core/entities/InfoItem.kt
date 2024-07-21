package core.entities

import androidx.compose.runtime.Immutable

@Immutable
data class InfoItem(
    val count: Int = 0,
    val pages: Int = 0,
    val next: String? = null,
    val prev: String? = null,
)
