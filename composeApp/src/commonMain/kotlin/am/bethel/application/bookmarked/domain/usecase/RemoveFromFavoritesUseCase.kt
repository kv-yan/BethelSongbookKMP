package am.bethel.application.bookmarked.domain.usecase

import am.bethel.application.common.domain.model.Song

interface RemoveFromFavoritesUseCase {
    suspend operator fun invoke(song: Song)

}