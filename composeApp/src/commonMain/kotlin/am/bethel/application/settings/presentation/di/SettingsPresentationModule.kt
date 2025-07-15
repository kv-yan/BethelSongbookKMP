package am.bethel.application.settings.presentation.di

import am.bethel.application.settings.presentation.SettingsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val settingsPresentationModule = module{
    singleOf(::SettingsViewModel)
}