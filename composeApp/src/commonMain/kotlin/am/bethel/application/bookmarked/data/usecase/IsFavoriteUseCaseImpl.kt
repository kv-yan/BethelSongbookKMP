package am.bethel.application.bookmarked.data.usecase

import am.bethel.application.bookmarked.domain.usecase.IsFavoriteUseCase
import am.bethel.application.common.domain.model.Song
import am.bethel.application.common.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow

class IsFavoriteUseCaseImpl(
    private val songRepository: SongRepository
) : IsFavoriteUseCase {
    override fun invoke(song: Song): Flow<Boolean> = songRepository.isFavorite(song)
}