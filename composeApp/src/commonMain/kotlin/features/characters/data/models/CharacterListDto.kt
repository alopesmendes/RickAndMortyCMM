package features.characters.data.models


import core.models.InfoDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterListDto(
    @SerialName("info")
    val info: InfoDto,
    @SerialName("results")
    val results: List<CharacterDto>
)