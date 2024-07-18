package features.episodes.data.models


import core.models.InfoDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeListDto(
    @SerialName("info")
    val info: InfoDto,
    @SerialName("results")
    val results: List<EpisodeDto>
)