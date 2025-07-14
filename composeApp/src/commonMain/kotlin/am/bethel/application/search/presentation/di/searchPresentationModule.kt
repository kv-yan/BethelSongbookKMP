package am.bethel.application.search.presentation.di

import am.bethel.application.search.presentation.SearchViewModel
import org.koin.dsl.module

val searchPresentationModule = module {
    single { SearchViewModel(get()) }

}