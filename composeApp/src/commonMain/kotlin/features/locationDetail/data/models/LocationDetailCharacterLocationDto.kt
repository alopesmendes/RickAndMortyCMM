package features.locationDetail.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDetailCharacterLocationDto(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)