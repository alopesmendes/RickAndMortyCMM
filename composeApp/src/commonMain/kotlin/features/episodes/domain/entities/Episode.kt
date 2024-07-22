package features.episodes.domain.entities

data class Episode(
    val id: Int,
    val name: String,
    val episode: String,
    val airDate: String,
    val url: String,
)
