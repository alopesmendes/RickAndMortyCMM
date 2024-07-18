import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import core.entities.Failure
import core.entities.State
import core.entities.toFailure
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import features.locationDetail.domain.entities.Resident
import features.locationDetail.domain.repositories.LocationDetailRepository
import features.locationDetail.domain.useCases.GetResidentsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetResidentsUseCaseTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var locationDetailRepository: LocationDetailRepository
    private lateinit var getResidentsUseCase: GetResidentsUseCase

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(dispatcher)
        locationDetailRepository = mock<LocationDetailRepository>()
        getResidentsUseCase = GetResidentsUseCase(locationDetailRepository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get residents when data available`() = runTest {
        // given:
        val ids = listOf(1, 2, 3)
        val residents = ids.map {
            Resident(
                id = it,
                name = "Resident $it",
                image = "https://example.com/image$it.jpg",
            )
        }
        val result = Result.success(residents)

        // when:
        everySuspend { locationDetailRepository.getResidents(any()) } returns result
        val actual = getResidentsUseCase(ids)

        // then:
        actual.test {
            assertThat(State.Loading).isEqualTo(awaitItem())
            assertThat(State.Success(residents)).isEqualTo(awaitItem())

            awaitComplete()
        }
    }

    @Test
    fun `should not get residents when data not available`() = runTest {
        // given:
        val ids = listOf(1, 2, 3)
        val exception = Exception()

        // when:
        everySuspend { locationDetailRepository.getResidents(any()) } returns Result.failure(Exception())
        val actual = getResidentsUseCase(ids)

        // then:
        actual.test {
            assertThat(State.Loading).isEqualTo(awaitItem())
            assertThat(State.Error(exception.toFailure())).isEqualTo(awaitItem())

            awaitComplete()
        }
    }
}