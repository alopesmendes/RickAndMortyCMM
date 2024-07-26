package features.episodeDetail.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDetailCharacterLocationDto(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)