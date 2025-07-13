package am.bethel.application.details.data.usecase

import am.bethel.application.details.domain.usecase.GetSongByIndexUseCase
import am.bethel.application.common.domain.model.Song
import am.bethel.application.common.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow

class GetSongByIndexUseCaseImpl(
    private val songRepository: SongRepository,
) : GetSongByIndexUseCase {
    override fun invoke(songIndex: Int): Flow<Song?> =
        songRepository.getByNumber(songIndex.toString())

}