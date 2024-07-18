package di

import features.characters.data.datasource.CharactersRemoteDatasource
import features.characters.data.datasource.CharactersRemoteDatasourceImpl
import features.characters.data.repositories.CharactersRepositoryImpl
import features.characters.domain.repositories.CharactersRepository
import features.characters.domain.useCases.GetCharactersListUseCase
import features.characters.presentation.viewModels.CharactersViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModel
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

    singleOf(::CharactersRepositoryImpl) { bind<CharactersRepository>() }
    singleOf(::GetCharactersListUseCase)

    viewModelOf(::CharactersViewModel)
}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}
