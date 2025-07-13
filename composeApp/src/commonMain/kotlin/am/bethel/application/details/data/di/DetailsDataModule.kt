package am.bethel.application.details.data.di

import am.bethel.application.details.data.usecase.GetSongByIndexUseCaseImpl
import am.bethel.application.details.domain.usecase.GetSongByIndexUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val detailsDataModule = module {
    factoryOf(::GetSongByIndexUseCaseImpl) { bind<GetSongByIndexUseCase>() }
}