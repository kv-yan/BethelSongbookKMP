package am.bethel.application.settings.data.di

import am.bethel.application.settings.data.usecase.GetThemeIndexUseCaseImpl
import am.bethel.application.settings.data.usecase.ChangeFontSizeUseCaseImpl
import am.bethel.application.settings.data.usecase.ChangeThemeIndexUseCaseImpl
import am.bethel.application.settings.data.usecase.GetFontSizeUseCaseImpl
import am.bethel.application.settings.data.usecase.InsertAllSongToDbUseCaseImpl
import am.bethel.application.settings.domain.usecase.ChangeFontSizeUseCase
import am.bethel.application.settings.domain.usecase.ChangeThemeIndexUseCase
import am.bethel.application.settings.domain.usecase.GetFontSizeUseCase
import am.bethel.application.settings.domain.usecase.GetThemeIndexUseCase
import am.bethel.application.settings.domain.usecase.InsertAllSongToDbUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val settingsDataModule = module {
    singleOf(::GetFontSizeUseCaseImpl) { bind<GetFontSizeUseCase>() }
    singleOf(::ChangeFontSizeUseCaseImpl) { bind<ChangeFontSizeUseCase>() }
    singleOf(::GetThemeIndexUseCaseImpl){bind<GetThemeIndexUseCase>()}
    singleOf(::ChangeThemeIndexUseCaseImpl){bind<ChangeThemeIndexUseCase>()}
    singleOf(::InsertAllSongToDbUseCaseImpl){bind<InsertAllSongToDbUseCase>()}
}