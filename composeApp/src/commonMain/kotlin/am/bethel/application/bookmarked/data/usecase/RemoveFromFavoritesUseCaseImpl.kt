package am.bethel.application.bookmarked.data.usecase

import am.bethel.application.bookmarked.domain.usecase.RemoveFromFavoritesUseCase
import am.bethel.application.common.domain.model.Song
import am.bethel.application.common.domain.repository.SongRepository

class RemoveFromFavoritesUseCaseImpl(
    private val songRepository: SongRepository,
) : RemoveFromFavoritesUseCase {
    override suspend fun invoke(song: Song) = songRepository.removeFromFavorites(song)
}