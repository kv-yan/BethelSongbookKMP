package am.bethel.application.bookmarked.data.usecase

import am.bethel.application.bookmarked.domain.usecase.GetFavoriteSongsUseCase
import am.bethel.application.common.domain.model.Song
import am.bethel.application.common.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteSongsUseCaseImpl(
    private val songRepository: SongRepository
) : GetFavoriteSongsUseCase {
    override fun invoke(): Flow<List<Song>> = songRepository.getFavoriteSongs()

}