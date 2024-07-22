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
import features.episodes.data.datasource.EpisodesRemoteDatasource
import features.episodes.data.models.EpisodeDto
import features.episodes.data.models.EpisodeListDto
import features.episodes.data.repositories.EpisodesRepositoryImpl
import features.episodes.domain.repositories.EpisodesRepository
import features.episodes.mapper.mapTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class EpisodesRepositoryImplTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var episodesRemoteDatasource: EpisodesRemoteDatasource
    private lateinit var episodesRepository: EpisodesRepository

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        episodesRemoteDatasource = mock()
        episodesRepository = EpisodesRepositoryImpl(episodesRemoteDatasource)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get a list of episodes when network call is successful`() = runTest {
        // given:
        val info = InfoDto(
            count = 2,
            pages = 1,
            next = "https://rickandmortyapi.com/api/episode/?page=2",
            prev = null
        )
        val episodes = listOf(
            EpisodeDto(
                id = 1,
                name = "Episode 1",
                airDate = "2017-01-01",
                episode = "S01E01",
                characters = listOf("https://rickandmortyapi.com/api/character/1"),
                url = "https://rickandmortyapi.com/api/episode/1",
                created = "2017-12-10T14:56:48.428Z",
            ),
            EpisodeDto(
                id = 2,
                name = "Episode 2",
                airDate = "2017-01-02",
                episode = "S01E02",
                characters = listOf("https://rickandmortyapi.com/api/character/2"),
                url = "https://rickandmortyapi.com/api/episode/2",
                created = "2017-12-10T14:56:48.428Z",
            )
        )
        val episodeListDto = EpisodeListDto(
            info = info,
            results = episodes,
        )

        // when:
        everySuspend { episodesRemoteDatasource.getEpisodeList(any()) } returns episodeListDto
        val actual = episodesRepository.getEpisodeList()

        // then:
        val expected = Result.success(episodeListDto.mapTo())
        verifySuspend(atMost(1)) { episodesRemoteDatasource.getEpisodeList(any()) }
        assertThat(actual).isEqualTo(expected)

    }

    @Test
    fun `should not get a list of episodes when network call returns nothing`() = runTest {
        // given:
        val info = InfoDto(
            count = 0,
            pages = 0,
            next = null,
            prev = null
        )
        val episodes = emptyList<EpisodeDto>()

        val episodeListDto = EpisodeListDto(
            info = info,
            results = episodes,
        )

        // when:
        everySuspend { episodesRemoteDatasource.getEpisodeList(any()) } returns episodeListDto
        val actual = episodesRepository.getEpisodeList()

        // then:
        val expected = Result.success(episodeListDto.mapTo())
        verifySuspend(atMost(1)) { episodesRemoteDatasource.getEpisodeList(any()) }
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should not get a list of episodes when network call fails`() = runTest {
        // given:
        val exception = Exception()

        // when:
        everySuspend { episodesRemoteDatasource.getEpisodeList(any()) } throws exception
        val actual = episodesRepository.getEpisodeList()

        // then:
        val expected = Result.failure<Exception>(exception)
        verifySuspend(atMost(1)) { episodesRemoteDatasource.getEpisodeList(any()) }
        assertThat(actual).isEqualTo(expected)
    }
}