package am.bethel.application.list.presentation.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import am.bethel.application.list.presentation.ListViewModel


val listPresentationModule = module {
    singleOf(::ListViewModel)
}