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
import features.characterDetail.domain.entities.CharacterDetail
import features.characterDetail.domain.entities.CharacterLocation
import features.characterDetail.domain.repositories.CharacterDetailRepository
import features.characterDetail.domain.useCases.GetCharacterDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class GetCharacterDetailUseCaseTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var characterDetailRepository: CharacterDetailRepository
    private lateinit var getCharacterDetailUseCase: GetCharacterDetailUseCase

    @BeforeTest
    fun setUp() {
        characterDetailRepository = mock()
        getCharacterDetailUseCase = GetCharacterDetailUseCase(characterDetailRepository)
        Dispatchers.setMain(dispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get character detail when data is available`() = runTest {
        // given:
        val id = 1
        val characterDetail = CharacterDetail(
            id = id,
            name = "",
            status = "",
            image = "",
            gender = "male",
            species = "",
            origin = CharacterLocation(name = "", url = ""),
            location = CharacterLocation(name = "", url = ""),
            episodes = emptyList(),
        )
        val result = Result.success(characterDetail)

        // when:
        everySuspend { characterDetailRepository.getCharacterDetail(any()) } returns result
        val actual = getCharacterDetailUseCase(id)

        // then:
        actual.test {
            verifySuspend(atMost(1)) { characterDetailRepository.getCharacterDetail(any()) }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Success(characterDetail))
            awaitComplete()
        }
    }

    @Test
    fun `should not get character detail when data is not available`() = runTest {
        // given:
        val id = 1
        val exception = Exception()
        val result = Result.failure<CharacterDetail>(exception)

        // when:
        everySuspend { characterDetailRepository.getCharacterDetail(any()) } returns result
        val actual = getCharacterDetailUseCase(id)

        // then:
        actual.test {
            verifySuspend(atMost(1)) { characterDetailRepository.getCharacterDetail(any()) }

            assertThat(awaitItem()).isEqualTo(State.Loading)
            assertThat(awaitItem()).isEqualTo(State.Error(exception.toFailure()))
            awaitComplete()
        }
    }
}