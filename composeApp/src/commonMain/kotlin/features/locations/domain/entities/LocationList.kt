package features.locations.domain.entities

import core.entities.Info

data class LocationList(
    val info: Info,
    val results: List<Location>
)
