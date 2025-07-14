package am.bethel.application.bookmarked.domain.usecase

import am.bethel.application.common.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface IsFavoriteUseCase {
    operator fun invoke(song: Song): Flow<Boolean>

}