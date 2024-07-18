package features.locations.domain.entities

data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val url: String,
)
