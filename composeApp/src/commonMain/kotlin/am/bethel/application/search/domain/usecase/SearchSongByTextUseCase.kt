package am.bethel.application.search.domain.usecase

import am.bethel.application.common.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface SearchSongByTextUseCase {
    operator fun invoke(query: String): Flow<List<Song>>
}