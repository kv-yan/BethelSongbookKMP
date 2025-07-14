package am.bethel.application.bookmarked.presentation.di

import am.bethel.application.bookmarked.presentation.BookmarkViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val bookmarkPresentationModule = module {
    singleOf(::BookmarkViewModel)
}