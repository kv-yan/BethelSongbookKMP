package am.bethel.application.details.presentation.di

import am.bethel.application.details.presentation.DetailsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val detailsPresentationModule = module{
    singleOf(::DetailsViewModel)
}