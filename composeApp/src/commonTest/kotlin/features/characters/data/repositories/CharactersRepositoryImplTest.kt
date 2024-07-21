import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import core.models.InfoDto
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.atMost
import dev.mokkery.verifySuspend
import features.characters.data.datasource.CharactersRemoteDatasource
import features.characters.data.models.CharacterDto
import features.characters.data.models.CharacterListDto
import features.characters.data.models.CharactersLocationDto
import features.characters.data.models.OriginDto
import features.characters.data.repositories.CharactersRepositoryImpl
import features.characters.domain.repositories.CharactersRepository
import features.characters.mapper.mapTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class CharactersRepositoryImplTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var charactersRemoteDatasource: CharactersRemoteDatasource
    private lateinit var charactersRepository: CharactersRepository

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        charactersRemoteDatasource = mock()
        charactersRepository = CharactersRepositoryImpl(charactersRemoteDatasource)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get a list of characters when network call is successful`() = runTest {
        // given:
        val page = "https://example.com/page/1"
        val location = CharactersLocationDto(
            name = "Earth",
            url = "https://example.com/earth",
        )
        val origin = OriginDto(
            name = "Earth",
            url = "https://example.com/earth",
        )
        val infoDto = InfoDto(
            count = 2,
            pages = 1,
            next = "https://example.com/next",
            prev = null,
        )
        val charactersDto = listOf(
            CharacterDto(
                id = 1,
                name = "Character 1",
                status = "Alive",
                species = "Human",
                gender = "Male",
                image = "https://example.com/character1.jpg",
                type = "",
                url = "https://example.com/character1",
                location = location,
                created = "",
                origin = origin,
                episode = emptyList(),
            ),
            CharacterDto(
                id = 2,
                name = "Character 2",
                status = "Alive",
                species = "Human",
                gender = "Male",
                image = "https://example.com/character2.jpg",
                type = "",
                url = "https://example.com/character2",
                location = location,
                created = "",
                origin = origin,
                episode = emptyList(),
            ),
        )
        val characterListDto = CharacterListDto(
            info = infoDto,
            results = charactersDto
        )

        // when:
        everySuspend { charactersRemoteDatasource.getCharacterList(any()) } returns characterListDto
        val actual = charactersRepository.getCharacters(page)

        // then:
        val expectedResult = Result.success(characterListDto.mapTo())
        verifySuspend(atMost(1)) {
            charactersRemoteDatasource.getCharacterList(any())
        }
        assertThat(actual).isEqualTo(expectedResult)

    }

    @Test
    fun `should not get a list of characters when network call returns nothing`() = runTest {
        // given:
        val page = "https://example.com/page/1"
        val infoDto = InfoDto(
            count = 0,
            pages = 1,
            next = null,
            prev = null,
        )
        val characterListDto = CharacterListDto(
            info = infoDto,
            results = emptyList()
        )

        // when:
        everySuspend { charactersRemoteDatasource.getCharacterList(any()) } returns characterListDto
        val actual = charactersRepository.getCharacters(page)

        // then:
        val expectedResult = Result.success(characterListDto.mapTo())
        verifySuspend(atMost(1)) {
            charactersRemoteDatasource.getCharacterList(any())
        }
        assertThat(actual).isEqualTo(expectedResult)

    }

    @Test
    fun `should not get a list of characters when network call fails`() = runTest {
        // given:
        val page = "https://example.com/page/1"
        val exception = IllegalStateException()

        // when:
        everySuspend { charactersRemoteDatasource.getCharacterList(any()) } throws exception
        val actual = charactersRepository.getCharacters(page)

        // then:
        verifySuspend(atMost(1)) {
            charactersRemoteDatasource.getCharacterList(any())
        }
        assertThat(actual).isFailure()
    }
}