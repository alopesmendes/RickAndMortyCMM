import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import core.entities.State
import core.entities.toFailure
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import features.locationDetail.domain.entities.LocationDetail
import features.locationDetail.domain.repositories.LocationDetailRepository
import features.locationDetail.domain.useCases.GetLocationDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetLocationDetailUseCaseTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var locationDetailRepository: LocationDetailRepository
    private lateinit var getLocationDetailUseCase: GetLocationDetailUseCase

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        locationDetailRepository = mock()
        getLocationDetailUseCase = GetLocationDetailUseCase(locationDetailRepository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get location detail when data available`() = runTest {
        // given:
        val id = 1
        val locationDetail = LocationDetail(
            id = id,
            name = "earth",
            dimension = "unknown",
            type = "",
            residents = emptyList(),
            url = ""
        )
        val result = Result.success(locationDetail)

        // when:
        everySuspend { locationDetailRepository.getLocationDetail(id) } returns result
        val actual = getLocationDetailUseCase(id)

        // then:
        actual.test {
            assertThat(State.Loading).isEqualTo(awaitItem())
            assertThat(State.Success(locationDetail)).isEqualTo(awaitItem())

            awaitComplete()
        }
    }

    @Test
    fun `should not get location detail when data not available`() = runTest {
        // given:
        val id = 1
        val exception = Exception()
        val result = Result.failure<LocationDetail>(exception)

        // when:
        everySuspend { locationDetailRepository.getLocationDetail(id) } returns result
        val actual = getLocationDetailUseCase(id)

        // then:
        actual.test {
            assertThat(State.Loading).isEqualTo(awaitItem())
            assertThat(State.Error(exception.toFailure())).isEqualTo(awaitItem())

            awaitComplete()
        }
    }
}