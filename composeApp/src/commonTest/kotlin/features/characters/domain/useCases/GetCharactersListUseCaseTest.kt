import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import core.entities.Failure
import core.entities.Info
import core.entities.State
import core.entities.toFailure
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.atMost
import dev.mokkery.verifySuspend
import features.characters.domain.entities.Character
import features.characters.domain.entities.CharacterList
import features.characters.domain.repositories.CharactersRepository
import features.characters.domain.useCases.GetCharactersListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetCharactersListUseCaseTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var charactersRepository: CharactersRepository
    private lateinit var getCharactersListUseCase: GetCharactersListUseCase

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        charactersRepository = mock<CharactersRepository>()
        getCharactersListUseCase = GetCharactersListUseCase(charactersRepository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get characters list when data available`() = runTest {
        // given:
        val page = "https://rickandmortyapi.com/api/character/?page=1"
        val characters = listOf(
            Character(id = 1, name = "name", gender = "male", image = "image"),
            Character(id = 2, name = "name", gender = "male", image = "image"),
            Character(id = 3, name = "name", gender = "male", image = "image"),
        )
        val info = Info(count = 3, pages = 1, next = "next", prev = "prev")
        val charactersList = CharacterList(
            results = characters,
            info = info,
        )
        val result = Result.success(charactersList)

        // when:
        everySuspend { charactersRepository.getCharacters(any()) } returns result
        val actual = getCharactersListUseCase(page)

        // then:
        actual.test {
            verifySuspend(atMost(1)) { charactersRepository.getCharacters(page) }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Success(charactersList))
            awaitComplete()
        }
    }

    @Test
    fun `should not get characters list when data not available`() = runTest {
        // given:
        val page = "https://rickandmortyapi.com/api/character/?page=1"
        val exception = Exception()
        val result = Result.failure<CharacterList>(exception)

        // when:
        everySuspend { charactersRepository.getCharacters(any()) } returns result
        val actual = getCharactersListUseCase(page)

        // then:
        actual.test {
            verifySuspend(atMost(1)) { charactersRepository.getCharacters(page) }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Error(exception.toFailure()))
            awaitComplete()
        }

    }
}