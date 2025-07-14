package am.bethel.application.details.domain.usecase

import am.bethel.application.common.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface GetSongByIndexUseCase {
    operator fun invoke(songIndex: Int): Flow<Song?>
}