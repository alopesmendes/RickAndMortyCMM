import assertk.assertThat
import assertk.assertions.isEqualTo
import core.models.InfoDto
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.atMost
import dev.mokkery.verifySuspend
import features.locations.data.datasource.LocationsRemoteDatasource
import features.locations.data.models.LocationDto
import features.locations.data.models.LocationListDto
import features.locations.data.repositories.LocationsRepositoryImpl
import features.locations.domain.repositories.LocationsRepository
import features.locations.mapper.mapTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class LocationsRepositoryImplTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var locationsRemoteDatasource: LocationsRemoteDatasource
    private lateinit var locationsRepository: LocationsRepository

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        locationsRemoteDatasource = mock()
        locationsRepository = LocationsRepositoryImpl(locationsRemoteDatasource)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get a list of locations when network call is successful`() = runTest {
        // given:
        val infoDto = InfoDto(
            count = 1,
            pages = 1,
            next = "https://rickandmortyapi.com/api/location/?page=2",
            prev = null
        )
        val locations = listOf(
            LocationDto(
                id = 1,
                name = "Earth (C-137)",
                type = "Planet",
                dimension = "Dimension C-137",
                residents = listOf("https://rickandmortyapi.com/api/character/1"),
                url = "https://rickandmortyapi.com/api/location/1",
                created = "2017-11-10T12:42:16.162Z",
            ),
            LocationDto(
                id = 2,
                name = "unknown",
                type = "Planet",
                dimension = "Dimension C-137",
                residents = listOf("https://rickandmortyapi.com/api/character/256"),
                url = "https://rickandmortyapi.com/api/location/2",
                created = "2017-11-10T12:42:16.162Z",
            )
        )
        val locationListDto = LocationListDto(
            info = infoDto,
            results = locations
        )

        // when:
        everySuspend { locationsRemoteDatasource.getLocationListDto(any()) } returns locationListDto
        val actual = locationsRepository.getLocationList()

        // then:
        val expected = Result.success(locationListDto.mapTo())
        verifySuspend(atMost(1)) { locationsRemoteDatasource.getLocationListDto(any()) }
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should not get a list of locations when network call returns nothing`() = runTest {
        // given:
        val info = InfoDto(
            count = 0,
            pages = 1,
            next = "https://rickandmortyapi.com/api/location/?page=2",
            prev = null
        )
        val locationListDto = LocationListDto(
            info = info,
            results = emptyList(),
        )

        // when:
        everySuspend { locationsRemoteDatasource.getLocationListDto(any()) } returns locationListDto
        val actual = locationsRepository.getLocationList()

        // then:
        val expected = Result.success(locationListDto.mapTo())
        verifySuspend(atMost(1)) { locationsRemoteDatasource.getLocationListDto(any()) }
        assertThat(actual).isEqualTo(expected)

    }

    @Test
    fun `should not get a list of characters when network call fails`() = runTest {
        // given:
        val exception = Exception()

        // when:
        everySuspend { locationsRemoteDatasource.getLocationListDto(any()) } throws exception
        val actual = locationsRepository.getLocationList()

        // then:
        val expected = Result.failure<LocationListDto>(exception)
        verifySuspend(atMost(1)) { locationsRemoteDatasource.getLocationListDto(any()) }
        assertThat(actual).isEqualTo(expected)
    }
}