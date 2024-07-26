package features.episodeDetail.domain.useCases

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import core.entities.State
import core.entities.toFailure
import core.util.Tools.extractIdFromUrl
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.atMost
import dev.mokkery.verifySuspend
import features.episodeDetail.domain.entities.EpisodeCharacter
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
class GetEpisodeCharactersUseCaseTest {
    private val dispatcher = Dispatchers.Unconfined
    private lateinit var episodeDetailRepository: EpisodeDetailRepository
    private lateinit var getEpisodeCharactersUseCase: GetEpisodeCharactersUseCase

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        episodeDetailRepository = mock()
        getEpisodeCharactersUseCase = GetEpisodeCharactersUseCase(episodeDetailRepository)
    }

    @AfterTest
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get episode characters when data available`() = runTest {
        // given:
        val characters = listOf(
            "https://rickandmortyapi.com/api/character/1",
            "https://rickandmortyapi.com/api/character/2",
        )
        val episodeCharacters = characters.map {
            EpisodeCharacter(
                id = it.extractIdFromUrl() ?: 0,
                name = "Rick",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
            )
        }
        val result = Result.success(episodeCharacters)

        // when:
        everySuspend { episodeDetailRepository.getCharacters(any()) } returns result
        val actual = getEpisodeCharactersUseCase(characters)

        // then:
        actual.test {
            verifySuspend(atMost(1)) { episodeDetailRepository.getCharacters(any()) }
            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(
                State.Success(episodeCharacters)
            )

            awaitComplete()
        }
    }

    @Test
    fun `should not get episode characters when data not available`() = runTest {
        // given:
        val exception = Exception()
        val characters = listOf(
            "https://rickandmortyapi.com/api/character/1",
            "https://rickandmortyapi.com/api/character/2",
        )
        val result = Result.failure<List<EpisodeCharacter>>(exception)

        // when:
        everySuspend { episodeDetailRepository.getCharacters(any()) } returns result
        val actual = getEpisodeCharactersUseCase(characters)

        // then:
        actual.test {
            verifySuspend(atMost(1)) { episodeDetailRepository.getCharacters(any()) }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(
                State.Error(exception.toFailure())
            )

            awaitComplete()
        }
    }
}