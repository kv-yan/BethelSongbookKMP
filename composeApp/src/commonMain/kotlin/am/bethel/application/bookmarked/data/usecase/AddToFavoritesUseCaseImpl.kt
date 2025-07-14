package am.bethel.application.bookmarked.data.usecase

import am.bethel.application.bookmarked.domain.usecase.AddToFavoritesUseCase
import am.bethel.application.common.domain.model.Song
import am.bethel.application.common.domain.repository.SongRepository

class AddToFavoritesUseCaseImpl(
    private val songRepository: SongRepository
) : AddToFavoritesUseCase {
    override suspend fun invoke(song: Song) = songRepository.addToFavorites(song)
}