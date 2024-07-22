package features.characterDetail.domain.entities

data class CharacterDetail(
    val id: Int,
    val name: String,
    val gender: String,
    val image: String,
    val status: String,
    val species: String,
    val origin: CharacterLocation,
    val location: CharacterLocation,
    val episodes: List<String>,
)
