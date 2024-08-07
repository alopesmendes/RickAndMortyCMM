package features.locationDetail.domain.entities

data class LocationDetail(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
)
