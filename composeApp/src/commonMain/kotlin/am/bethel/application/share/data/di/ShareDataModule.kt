package am.bethel.application.share.data.di

import am.bethel.application.share.data.usecase.ShareSongUseCaseImpl
import am.bethel.application.share.domain.usecase.ShareSongUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val shareDataModule = module {
    factoryOf(::ShareSongUseCaseImpl) { bind<ShareSongUseCase>() }
}