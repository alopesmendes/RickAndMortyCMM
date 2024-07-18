package di

import features.characters.data.repositories.CharactersRepositoryImpl
import features.characters.domain.repositories.CharactersRepository
import features.characters.domain.useCases.GetCharactersListUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::CharactersRepositoryImpl) { bind<CharactersRepository>() }
    single<GetCharactersListUseCase> { GetCharactersListUseCase(get()) }
}