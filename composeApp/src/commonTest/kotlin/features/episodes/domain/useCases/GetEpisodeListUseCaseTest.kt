import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import core.entities.Info
import core.entities.State
import core.entities.toFailure
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode
import dev.mokkery.verify.VerifyMode.Companion.atMost
import dev.mokkery.verifySuspend
import features.episodes.domain.entities.Episode
import features.episodes.domain.entities.EpisodeList
import features.episodes.domain.repositories.EpisodesRepository
import features.episodes.domain.useCases.GetEpisodeListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetEpisodeListUseCaseTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var episodesRepository: EpisodesRepository
    private lateinit var getEpisodeListUseCase: GetEpisodeListUseCase

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        episodesRepository = mock()
        getEpisodeListUseCase = GetEpisodeListUseCase(episodesRepository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get characters list when data available`() = runTest {
        // given:
        val info = Info(
            count = 2,
            pages = 1,
            next = "https://rickandmortyapi.com/api/episode?page=2",
            prev = null,
        )
        val episodes = listOf(
            Episode(
                id = 1,
                name = "Episode 1",
                airDate = "2017-11-10",
                episode = "S01E01",
                characters = emptyList(),
                url = "https://rickandmortyapi.com/api/episode/1",
            ),
            Episode(
                id = 2,
                name = "Episode 2",
                airDate = "2017-11-11",
                episode = "S01E02",
                characters = emptyList(),
                url = "https://rickandmortyapi.com/api/episode/2",
            )
        )
        val episodeList = EpisodeList(
            info = info,
            results = episodes,
        )

        val result = Result.success(episodeList)

        // when:
        everySuspend { episodesRepository.getEpisodeList(any()) } returns result
        val actual = getEpisodeListUseCase()

        // then:
        actual.test {
            verifySuspend(atMost(1)) { episodesRepository.getEpisodeList(any()) }
            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Success(episodeList))

            awaitComplete()
        }
    }

    @Test
    fun `should not get characters list when data not available`() = runTest {
        // given:
        val exception = Exception()

        // when:
        everySuspend { episodesRepository.getEpisodeList(any()) } returns Result.failure(exception)
        val actual = getEpisodeListUseCase()

        // then:
        actual.test {
            verifySuspend(atMost(1)) { episodesRepository.getEpisodeList(any()) }
            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Error(exception.toFailure()))

            awaitComplete()
        }

    }
}