import core.entities.State
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import features.locationDetail.domain.entities.LocationDetail
import features.locationDetail.domain.entities.Resident
import features.locationDetail.domain.repositories.LocationDetailRepository
import features.locationDetail.domain.useCases.GetLocationDetailUseCase
import features.locationDetail.domain.useCases.GetLocationDetailWithResidentsUseCase
import features.locationDetail.domain.useCases.GetResidentsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetLocationDetailWithResidentsUseCaseTest {
    private val dispatcher = Dispatchers.Unconfined
    private lateinit var getLocationDetailUseCase: GetLocationDetailUseCase
    private lateinit var getResidentsUseCase: GetResidentsUseCase
    private lateinit var getLocationDetailWithResidentsUseCase: GetLocationDetailWithResidentsUseCase

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(dispatcher)

//        getLocationDetailUseCase = mock<GetLocationDetailUseCase>()
//        getResidentsUseCase = mock<GetResidentsUseCase>()
//        getLocationDetailWithResidentsUseCase = GetLocationDetailWithResidentsUseCase(
//            getLocationDetailUseCase = getLocationDetailUseCase,
//            getResidentsUseCase = getResidentsUseCase,
//        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get location detail with residents when data available`() = runTest {
        // given:

        // when:

        // then:
    }

    @Test
    fun `should not get location detail with residents when data not available`() = runTest {
        // given:

        // when:

        // then:
    }

    @Test
    fun `should get only location detail when residents not available`() = runTest {
        // given:

        // when:

        // then:
    }
}