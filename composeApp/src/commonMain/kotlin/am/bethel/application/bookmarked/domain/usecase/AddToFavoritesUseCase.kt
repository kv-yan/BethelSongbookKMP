package am.bethel.application.bookmarked.domain.usecase

import am.bethel.application.common.domain.model.Song


interface AddToFavoritesUseCase {
    suspend operator fun invoke(song: Song)
}