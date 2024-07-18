package features.locationDetail.domain.entities

data class LocationDetailWithResidents(
    val locationDetail: LocationDetail,
    val residents: List<Resident>
)
