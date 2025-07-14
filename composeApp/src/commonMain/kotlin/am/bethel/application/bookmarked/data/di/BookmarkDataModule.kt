package am.bethel.application.bookmarked.data.di

import am.bethel.application.bookmarked.data.usecase.AddToFavoritesUseCaseImpl
import am.bethel.application.bookmarked.data.usecase.GetFavoriteSongsUseCaseImpl
import am.bethel.application.bookmarked.domain.usecase.AddToFavoritesUseCase
import am.bethel.application.bookmarked.data.usecase.IsFavoriteUseCaseImpl
import am.bethel.application.bookmarked.data.usecase.RemoveFromFavoritesUseCaseImpl
import am.bethel.application.bookmarked.domain.usecase.GetFavoriteSongsUseCase
import am.bethel.application.bookmarked.domain.usecase.IsFavoriteUseCase
import am.bethel.application.bookmarked.domain.usecase.RemoveFromFavoritesUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val bookmarkDataModule = module {
    singleOf(::AddToFavoritesUseCaseImpl) { bind<AddToFavoritesUseCase>() }
    singleOf(::GetFavoriteSongsUseCaseImpl) { bind<GetFavoriteSongsUseCase>() }
    singleOf(::RemoveFromFavoritesUseCaseImpl) { bind<RemoveFromFavoritesUseCase>() }
    singleOf(::IsFavoriteUseCaseImpl) { bind<IsFavoriteUseCase>() }
}