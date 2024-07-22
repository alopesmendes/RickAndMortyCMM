import assertk.assertThat
import assertk.assertions.isEqualTo
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.atMost
import dev.mokkery.verifySuspend
import features.characterDetail.data.datasource.CharacterDetailRemoteDatasource
import features.characterDetail.data.models.CharacterDetailDto
import features.characterDetail.data.models.CharacterDetailLocationDto
import features.characterDetail.data.repositories.CharacterDetailRepositoryImpl
import features.characterDetail.domain.repositories.CharacterDetailRepository
import features.characterDetail.mapper.mapTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class CharacterDetailRepositoryImplTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var characterDetailRemoteDatasource: CharacterDetailRemoteDatasource
    private lateinit var characterDetailRepository: CharacterDetailRepository

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        characterDetailRemoteDatasource = mock()
        characterDetailRepository = CharacterDetailRepositoryImpl(characterDetailRemoteDatasource)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get character detail when network call is successful`() = runTest {
        // given:
        val characterDetailLocationDto = CharacterDetailLocationDto(
            name = "Peggy Pollard",
            url = "https://www.google.com/#q=intellegat"
        )
        val originDto = CharacterDetailLocationDto(
            name = "Leopoldo Blanchard",
            url = "https://search.yahoo.com/search?p=hendrerit"
        )
        val id = 1
        val characterDetailDto = CharacterDetailDto(
            created = "senectus",
            episodes = listOf(),
            gender = "fastidii",
            id = id,
            image = "novum",
            location = characterDetailLocationDto,
            name = "Janis Gaines",
            origin = originDto,
            species = "nec",
            status = "amet",
            type = "netus",
            url = "https://duckduckgo.com/?q=legimus"
        )

        // when:
        everySuspend { characterDetailRemoteDatasource.getCharacterDetail(any()) } returns characterDetailDto
        val actual = characterDetailRepository.getCharacterDetail(1)

        // then:
        val expectResult = Result.success(characterDetailDto.mapTo())
        verifySuspend(atMost(1)) { characterDetailRemoteDatasource.getCharacterDetail(any()) }
        assertThat(actual).isEqualTo(expectResult)
    }

    @Test
    fun `should return character detail when network call fails`() = runTest {
        // given:
        val exception = Exception()
        val id = 1

        // when:
        everySuspend { characterDetailRemoteDatasource.getCharacterDetail(any()) } throws exception
        val actual = characterDetailRepository.getCharacterDetail(id)

        // then:
        val expectResult = Result.failure<CharacterDetailDto>(exception)
        verifySuspend(atMost(1)) { characterDetailRemoteDatasource.getCharacterDetail(any()) }
        assertThat(actual).isEqualTo(expectResult)
    }
}