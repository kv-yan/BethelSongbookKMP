package am.bethel.application.bookmarked.domain.usecase

import am.bethel.application.common.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface GetFavoriteSongsUseCase {
    operator fun invoke(): Flow<List<Song>>
}