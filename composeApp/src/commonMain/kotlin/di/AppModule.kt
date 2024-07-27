package di

import features.characterDetail.data.datasource.CharacterDetailRemoteDatasource
import features.characterDetail.data.datasource.CharacterDetailRemoteDatasourceImpl
import features.characterDetail.data.repositories.CharacterDetailRepositoryImpl
import features.characterDetail.domain.repositories.CharacterDetailRepository
import features.characterDetail.domain.useCases.GetCharacterDetailUseCase
import features.characterDetail.presentation.viewModels.CharacterDetailViewModel
import features.characters.data.datasource.CharactersRemoteDatasource
import features.characters.data.datasource.CharactersRemoteDatasourceImpl
import features.characters.data.repositories.CharactersRepositoryImpl
import features.characters.domain.repositories.CharactersRepository
import features.characters.domain.useCases.GetCharactersListUseCase
import features.characters.presentation.viewModels.CharacterListViewModel
import features.episodeDetail.data.datasource.EpisodeDetailRemoteDatasource
import features.episodeDetail.data.datasource.EpisodeDetailRemoteDatasourceImpl
import features.episodeDetail.data.repositories.EpisodeDetailRepositoryImpl
import features.episodeDetail.domain.repositories.EpisodeDetailRepository
import features.episodeDetail.domain.useCases.GetEpisodeCharactersUseCase
import features.episodeDetail.domain.useCases.GetEpisodeDetailUseCase
import features.episodeDetail.presentation.viewModels.EpisodeDetailViewModel
import features.episodes.data.datasource.EpisodesRemoteDatasource
import features.episodes.data.datasource.EpisodesRemoteDatasourceImpl
import features.episodes.data.repositories.EpisodesRepositoryImpl
import features.episodes.domain.repositories.EpisodesRepository
import features.episodes.domain.useCases.GetEpisodeListUseCase
import features.episodes.presentation.viewModels.EpisodeListViewModel
import features.locationDetail.data.datasource.LocationDetailDatasource
import features.locationDetail.data.datasource.LocationDetailDatasourceImpl
import features.locationDetail.data.repositories.LocationDetailRepositoryImpl
import features.locationDetail.domain.repositories.LocationDetailRepository
import features.locationDetail.domain.useCases.GetLocationDetailUseCase
import features.locationDetail.domain.useCases.GetResidentsUseCase
import features.locationDetail.presentation.viewModels.LocationDetailViewModel
import features.locations.data.datasource.LocationsRemoteDatasource
import features.locations.data.datasource.LocationsRemoteDatasourceImpl
import features.locations.data.repositories.LocationsRepositoryImpl
import features.locations.domain.repositories.LocationsRepository
import features.locations.domain.useCases.GetLocationListUseCase
import features.locations.presentation.viewModels.LocationsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                })
            }
        }
    }

    singleOf(::CharactersRemoteDatasourceImpl) { bind<CharactersRemoteDatasource>() }
    singleOf(::CharacterDetailRemoteDatasourceImpl) { bind<CharacterDetailRemoteDatasource>() }
    singleOf(::EpisodesRemoteDatasourceImpl) { bind<EpisodesRemoteDatasource>() }
    singleOf(::LocationsRemoteDatasourceImpl) { bind<LocationsRemoteDatasource>() }
    singleOf(::EpisodeDetailRemoteDatasourceImpl) { bind<EpisodeDetailRemoteDatasource>() }
    singleOf(::LocationDetailDatasourceImpl) { bind<LocationDetailDatasource>() }

    singleOf(::CharactersRepositoryImpl) { bind<CharactersRepository>() }
    singleOf(::CharacterDetailRepositoryImpl) { bind<CharacterDetailRepository>() }
    singleOf(::EpisodesRepositoryImpl) { bind<EpisodesRepository>() }
    singleOf(::LocationsRepositoryImpl) { bind<LocationsRepository>() }
    singleOf(::EpisodeDetailRepositoryImpl) { bind<EpisodeDetailRepository>() }
    singleOf(::LocationDetailRepositoryImpl) { bind<LocationDetailRepository>() }

    singleOf(::GetCharactersListUseCase)
    singleOf(::GetCharacterDetailUseCase)
    singleOf(::GetEpisodeListUseCase)
    singleOf(::GetLocationListUseCase)
    singleOf(::GetEpisodeDetailUseCase)
    singleOf(::GetEpisodeCharactersUseCase)
    singleOf(::GetLocationDetailUseCase)
    singleOf(::GetResidentsUseCase)

    viewModelOf(::CharacterListViewModel)
    viewModelOf(::CharacterDetailViewModel)
    viewModelOf(::EpisodeListViewModel)
    viewModelOf(::LocationsViewModel)
    viewModelOf(::EpisodeDetailViewModel)
    viewModelOf(::LocationDetailViewModel)
}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}
