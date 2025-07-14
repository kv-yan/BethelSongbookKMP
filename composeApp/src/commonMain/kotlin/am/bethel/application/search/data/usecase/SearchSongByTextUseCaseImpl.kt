package am.bethel.application.search.data.usecase

import am.bethel.application.common.domain.model.Song
import am.bethel.application.common.domain.repository.SongRepository
import am.bethel.application.search.domain.usecase.SearchSongByTextUseCase
import kotlinx.coroutines.flow.Flow

class SearchSongByTextUseCaseImpl(
    private val songRepository: SongRepository,
) : SearchSongByTextUseCase {
    override fun invoke(query: String): Flow<List<Song>> = songRepository.search(query)
}