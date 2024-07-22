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
import features.episodes.data.datasource.EpisodesRemoteDatasource
import features.episodes.data.datasource.EpisodesRemoteDatasourceImpl
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

    singleOf(::CharactersRepositoryImpl) { bind<CharactersRepository>() }
    singleOf(::CharacterDetailRepositoryImpl) { bind<CharacterDetailRepository>() }

    singleOf(::GetCharactersListUseCase)
    singleOf(::GetCharacterDetailUseCase)

    viewModelOf(::CharacterListViewModel)
    viewModelOf(::CharacterDetailViewModel)
}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}
