package features.characterDetail.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailDto(
    @SerialName("created")
    val created: String,
    @SerialName("episode")
    val episodes: List<String>,
    @SerialName("gender")
    val gender: String,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("location")
    val location: CharacterDetailLocationDto,
    @SerialName("name")
    val name: String,
    @SerialName("origin")
    val origin: CharacterDetailLocationDto,
    @SerialName("species")
    val species: String,
    @SerialName("status")
    val status: String,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String
)