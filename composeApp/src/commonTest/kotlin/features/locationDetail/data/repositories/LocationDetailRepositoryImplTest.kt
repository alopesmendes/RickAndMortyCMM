package features.locationDetail.data.repositories

import assertk.assertThat
import assertk.assertions.isEqualTo
import core.util.Tools.extractIdFromUrl
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.atMost
import dev.mokkery.verifySuspend
import features.characterDetail.data.models.CharacterDetailDto
import features.episodeDetail.data.models.EpisodeDetailCharacterDto
import features.episodeDetail.data.models.EpisodeDetailCharacterLocationDto
import features.episodeDetail.mapper.mapTo
import features.locationDetail.data.datasource.LocationDetailDatasource
import features.locationDetail.data.models.LocationDetailCharacterDto
import features.locationDetail.data.models.LocationDetailCharacterLocationDto
import features.locationDetail.data.models.LocationDetailDto
import features.locationDetail.domain.repositories.LocationDetailRepository
import features.locationDetail.mapper.mapTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@ExperimentalCoroutinesApi
class LocationDetailRepositoryImplTest {
    private val dispatcher = Dispatchers.Unconfined

    private lateinit var locationDetailRemoteDatasource: LocationDetailDatasource
    private lateinit var locationDetailRepository: LocationDetailRepository

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        locationDetailRemoteDatasource = mock()
        locationDetailRepository = LocationDetailRepositoryImpl(locationDetailRemoteDatasource)
    }

    @AfterTest
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get location detail when network call is successful`() = runTest {
        // given:
        val id = 1
        val episodeDetailDto = LocationDetailDto(
            created = "ante",
            dimension = "putent",
            id = id,
            name = "Neal Calhoun",
            residents = listOf(),
            type = "pro",
            url = "https://duckduckgo.com/?q=principes"
        )

        // when:
        everySuspend { locationDetailRemoteDatasource.getLocationDetail(any()) } returns episodeDetailDto
        val actual = locationDetailRepository.getLocationDetail(id)

        // then:
        val expected = Result.success(episodeDetailDto.mapTo())

        verifySuspend(atMost(1)) { locationDetailRemoteDatasource.getLocationDetail(any()) }
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should return location detail when network call fails`() = runTest {
        // given:
        val exception = Exception()
        val episodeId = 1

        // when:
        everySuspend { locationDetailRemoteDatasource.getLocationDetail(any()) } throws exception
        val actual = locationDetailRepository.getLocationDetail(episodeId)

        // then:
        val expected = Result.failure<CharacterDetailDto>(exception)

        verifySuspend(atMost(1)) { locationDetailRemoteDatasource.getLocationDetail(any()) }
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should get location detail characters when network call is successful`() = runTest {
        // given:
        val characters = listOf(
            "https://rickandmortyapi.com/api/character/1",
            "https://rickandmortyapi.com/api/character/2"
        )
        val episodeCharactersDto = characters.map {
            LocationDetailCharacterDto(
                id = it.extractIdFromUrl() ?: 0,
                name = "William",
                episode = emptyList(),
                url = it,
                created = "2017-11-04T18:48:46.250Z",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                status = "Alive",
                gender = "Male",
                type = "Human",
                species = "Human",
                location = LocationDetailCharacterLocationDto(
                    name = "Earth (C-137)",
                    url = "https://rickandmortyapi.com/api/location/1"
                ),
                origin = LocationDetailCharacterLocationDto(
                    name = "Earth (Replacement Dimension)",
                    url = "https://rickandmortyapi.com/api/location/20"
                )
            )
        }

        // when:
        everySuspend { locationDetailRemoteDatasource.getCharacters(any()) } returns episodeCharactersDto
        val actual = locationDetailRepository.getResidents(characters)

        // then:
        val expected = Result.success(episodeCharactersDto.mapTo())

        verifySuspend(atMost(1)) { locationDetailRemoteDatasource.getCharacters(any()) }
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should return location detail characters when network call fails`() = runTest {
        // given:
        val exception = Exception()
        val characters = listOf(
            "https://rickandmortyapi.com/api/character/1",
            "https://rickandmortyapi.com/api/character/2"
        )

        // when:
        everySuspend { locationDetailRemoteDatasource.getCharacters(any()) } throws exception
        val actual = locationDetailRepository.getResidents(characters)

        // then:
        val expected = Result.failure<List<EpisodeDetailCharacterDto>>(exception)

        verifySuspend(atMost(1)) { locationDetailRemoteDatasource.getCharacters(any()) }
        assertThat(actual).isEqualTo(expected)
    }
}