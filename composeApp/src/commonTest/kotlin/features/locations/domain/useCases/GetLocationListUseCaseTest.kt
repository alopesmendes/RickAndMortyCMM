import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import core.entities.Failure
import core.entities.Info
import core.entities.State
import core.entities.toFailure
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode
import dev.mokkery.verify.VerifyMode.Companion.atMost
import dev.mokkery.verifySuspend
import features.locations.domain.entities.Location
import features.locations.domain.entities.LocationList
import features.locations.domain.repositories.LocationsRepository
import features.locations.domain.useCases.GetLocationListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetLocationListUseCaseTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var locationsRepository: LocationsRepository
    private lateinit var getLocationListUseCase: GetLocationListUseCase

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        locationsRepository = mock<LocationsRepository>()
        getLocationListUseCase = GetLocationListUseCase(locationsRepository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get location list when data available`() = runTest {
        // given:
        val page = 1
        val locationList = LocationList(
            info = Info(
                count = 1,
                pages = 1,
                next = "https://rickandmortyapi.com/api/location/?page=2",
                prev = null
            ),
            results = listOf(
                Location(
                    id = 1,
                    name = "Earth (C-137)",
                    type = "Planet",
                    dimension = "Dimension C-137",
                    url = "https://rickandmortyapi.com/api/location/1"
                )
            ),
        )
        val result = Result.success(locationList)

        // when:
        everySuspend { locationsRepository.getLocationList(any()) } returns result
        val actualResult = getLocationListUseCase(page)

        // then:
        actualResult.test {
            verifySuspend(atMost(1)) { locationsRepository.getLocationList(any()) }
            assertThat(State.Loading).isEqualTo(awaitItem())
            assertThat(State.Success(locationList)).isEqualTo(awaitItem())

            awaitComplete()
        }
    }

    @Test
    fun `should not get location list when data not available`() = runTest {
        // given:
        val exception = Exception()
        val result = Result.failure<LocationList>(exception)
        val page = 1

        // when:
        everySuspend { locationsRepository.getLocationList(any()) } returns result
        val actualResult = getLocationListUseCase(page)

        // then:
        actualResult.test {
            verifySuspend(atMost(1)) { locationsRepository.getLocationList(any()) }
            assertThat(State.Loading).isEqualTo(awaitItem())
            assertThat(State.Error(exception.toFailure())).isEqualTo(awaitItem())

            awaitComplete()
        }
    }

    @Test
    fun `should fail when page is negative`() = runTest {
        // given:
        val page = -1
        val locationList = LocationList(
            info = Info(
                count = 1,
                pages = 1,
                next = "https://rickandmortyapi.com/api/location/?page=2",
                prev = null
            ),
            results = listOf(
                Location(
                    id = 1,
                    name = "Earth (C-137)",
                    type = "Planet",
                    dimension = "Dimension C-137",
                    url = "https://rickandmortyapi.com/api/location/1"
                )
            ),
        )
        val result = Result.success(locationList)

        // when:
        everySuspend { locationsRepository.getLocationList(any()) } returns result
        val actualResult = getLocationListUseCase(page)

        // then:
        actualResult.test {
            assertThat(State.Error(Failure.AssertionFailure)).isEqualTo(awaitItem())

            awaitComplete()
        }
    }
}