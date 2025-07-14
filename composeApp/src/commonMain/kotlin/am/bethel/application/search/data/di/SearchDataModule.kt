package am.bethel.application.search.data.di

import am.bethel.application.search.data.usecase.SearchSongByTextUseCaseImpl
import am.bethel.application.search.domain.usecase.SearchSongByTextUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val searchDataModule = module {
    factoryOf(::SearchSongByTextUseCaseImpl){bind<SearchSongByTextUseCase>()}
}