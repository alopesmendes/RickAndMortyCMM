import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.atMost
import dev.mokkery.verifySuspend
import features.characterDetail.data.models.CharacterDetailDto
import features.episodeDetail.data.datasource.EpisodeDetailRemoteDatasource
import features.episodeDetail.data.models.EpisodeDetailDto
import features.episodeDetail.data.repositories.EpisodeDetailRepositoryImpl
import features.episodeDetail.domain.repositories.EpisodeDetailRepository
import features.episodeDetail.mapper.mapTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class EpisodeDetailRepositoryImplTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var episodeDetailRemoteDatasource: EpisodeDetailRemoteDatasource
    private lateinit var episodeDetailRepository: EpisodeDetailRepository

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        episodeDetailRemoteDatasource = mock()
        episodeDetailRepository = EpisodeDetailRepositoryImpl(episodeDetailRemoteDatasource)
    }

    @AfterTest
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get episode detail when network call is successful`() = runTest {
        // given:
        val episodeId = 1
        val episodeDetailDto = EpisodeDetailDto(
            airDate = "torquent",
            characters = listOf(),
            created = "mandamus",
            episode = "eruditi",
            id = episodeId,
            name = "Cherry Harding",
            url = "http://www.bing.com/search?q=veritus"
        )

        // when:
        everySuspend { episodeDetailRemoteDatasource.getEpisodeDetail(any()) } returns episodeDetailDto
        val actual = episodeDetailRepository.getEpisodeDetail(episodeId)

        // then:
        val expected = Result.success(episodeDetailDto.mapTo())

        verifySuspend(atMost(1)) { episodeDetailRemoteDatasource.getEpisodeDetail(any()) }
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should return episode detail when network call fails`() = runTest {
        // given:
        val exception = Exception()
        val episodeId = 1

        // when:
        everySuspend { episodeDetailRemoteDatasource.getEpisodeDetail(any()) } throws exception
        val actual = episodeDetailRepository.getEpisodeDetail(episodeId)

        // then:
        val expected = Result.failure<CharacterDetailDto>(exception)

        verifySuspend(atMost(1)) { episodeDetailRemoteDatasource.getEpisodeDetail(any()) }
        assertThat(actual).isEqualTo(expected)
    }
}