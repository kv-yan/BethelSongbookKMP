package am.bethel.application.koin

import am.bethel.application.bookmarked.data.di.bookmarkDataModule
import am.bethel.application.bookmarked.presentation.di.bookmarkPresentationModule
import am.bethel.application.common.data.di.commonDataModule
import am.bethel.application.details.data.di.detailsDataModule
import am.bethel.application.details.presentation.di.detailsPresentationModule
import am.bethel.application.list.presentation.di.listPresentationModule
import am.bethel.application.search.data.di.searchDataModule
import am.bethel.application.search.presentation.di.searchPresentationModule
import am.bethel.application.settings.data.di.settingsDataModule
import am.bethel.application.settings.presentation.di.settingsPresentationModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(vararg modules: Module) {
    startKoin {
        modules(
            listOf(
                appModule,
                listPresentationModule,
                commonDataModule,
                detailsDataModule,
                detailsPresentationModule,
                bookmarkDataModule,
                bookmarkPresentationModule,
                searchDataModule,
                searchPresentationModule,
                settingsDataModule,
                settingsPresentationModule
            ) + modules
        )
    }
}