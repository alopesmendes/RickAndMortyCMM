package features.episodeDetail.domain.useCases

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import core.entities.State
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import features.episodeDetail.domain.entities.EpisodeCharacter
import features.episodeDetail.domain.entities.EpisodeDetail
import features.episodeDetail.domain.entities.EpisodeDetailWithEpisodeCharacters
import features.episodeDetail.domain.repositories.EpisodeDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetEpisodeDetailWithEpisodeCharactersUseCaseTest {
    private val dispatcher = Dispatchers.Unconfined
    private lateinit var episodeDetailRepository: EpisodeDetailRepository
    private lateinit var getEpisodeCharactersUseCase: GetEpisodeCharactersUseCase
    private lateinit var getEpisodeDetailUseCase: GetEpisodeDetailUseCase
    private lateinit var getEpisodeDetailWithEpisodeCharactersUseCase: GetEpisodeDetailWithEpisodeCharactersUseCase

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        episodeDetailRepository = mock()
        getEpisodeDetailUseCase = GetEpisodeDetailUseCase(episodeDetailRepository)
        getEpisodeCharactersUseCase = GetEpisodeCharactersUseCase(episodeDetailRepository)
        getEpisodeDetailWithEpisodeCharactersUseCase = GetEpisodeDetailWithEpisodeCharactersUseCase(
            getEpisodeDetailUseCase = getEpisodeDetailUseCase,
            getEpisodeCharactersUseCase = getEpisodeCharactersUseCase,
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get episode detail with episode characters when both data is available`() = runTest {
        // given:
        val episodeId = 1
        val episodeDetail = EpisodeDetail(
            id = episodeId,
            episode = "S01E01",
            name = "Episode 1",
            airDate = "2023-01-01",
            url = "https://example.com/episode/1",
            created = "2023-01-01T00:00:00Z",
            characters = listOf("https://example.com/character/1", "https://example.com/character/2")
        )
        val episodeCharacters = listOf(
            EpisodeCharacter(
                id = 1,
                name = "Character 1",
                image = "https://example.com/character/1.jpg",
            ),
            EpisodeCharacter(
                id = 2,
                name = "Character 2",
                image = "https://example.com/character/2.jpg",
            )
        )

        val episodeDetailResult = Result.success(episodeDetail)
        val episodeCharactersResult = Result.success(episodeCharacters)

        // when:
        everySuspend { episodeDetailRepository.getEpisodeDetail(any()) } returns episodeDetailResult
        everySuspend { episodeDetailRepository.getCharacters(any()) } returns episodeCharactersResult

        val actual = getEpisodeDetailWithEpisodeCharactersUseCase(episodeId)

        // then:
        val expected = State.Success(
            EpisodeDetailWithEpisodeCharacters(
                episodeDetail = episodeDetail,
                episodeCharacters = episodeCharacters
            )
        )
        actual.test {
            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `should get episode detail without characters when only detail is available`() = runTest {

    }

    @Test
    fun `should not get episode detail and characters when both data is not available`() = runTest {

    }

    @Test
    fun `should not get episode detail and characters when detail is not available`() = runTest {

    }

    @Test
    fun `should not get episode detail and characters when data not available`() = runTest {

    }

}