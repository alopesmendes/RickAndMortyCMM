import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import core.entities.State
import core.entities.toFailure
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.atMost
import dev.mokkery.verifySuspend
import features.episodeDetail.domain.entities.EpisodeDetail
import features.episodeDetail.domain.repositories.EpisodeDetailRepository
import features.episodeDetail.domain.useCases.GetEpisodeDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetEpisodeDetailUseCaseTest {
    private val dispatcher = Dispatchers.Unconfined
    private lateinit var episodeDetailRepository: EpisodeDetailRepository
    private lateinit var getEpisodeDetailUseCase: GetEpisodeDetailUseCase

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        episodeDetailRepository = mock()
        getEpisodeDetailUseCase = GetEpisodeDetailUseCase(episodeDetailRepository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get episode detail when data available`() = runTest {
        // given:
        val episodeId = 1
        val episodeDetail = EpisodeDetail(
            id = episodeId,
            episode = "S01E01",
            name = "Episode 1",
            airDate = "2023-01-01",
            url = "https://example.com/episode1",
            created = "2023-01-01T00:00:00Z"
        )
        val result = Result.success(episodeDetail)

        // when:
        everySuspend { episodeDetailRepository.getEpisodeDetail(any()) } returns result
        val actual = getEpisodeDetailUseCase(episodeId)

        // then:
        actual.test {
            verifySuspend(atMost(1)) { episodeDetailRepository.getEpisodeDetail(any()) }
            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(
                State.Success(episodeDetail)
            )

            awaitComplete()
        }
    }

    @Test
    fun `should not get episode detail when data not available`() = runTest {
        // given:
        val exception = Exception()
        val result = Result.failure<EpisodeDetail>(exception)

        // when:
        everySuspend { episodeDetailRepository.getEpisodeDetail(any()) } returns result
        val actual = getEpisodeDetailUseCase(1)

        // then:
        actual.test {
            verifySuspend(atMost(1)) { episodeDetailRepository.getEpisodeDetail(any()) }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(
                State.Error(exception.toFailure())
            )

            awaitComplete()
        }
    }
}