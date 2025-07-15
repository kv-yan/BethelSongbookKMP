package am.bethel.application.settings.domain.usecase

import am.bethel.application.common.domain.repository.SongRepository

interface InsertAllSongToDbUseCase {
    suspend operator fun invoke()
}

class InsertAllSongToDbUseCaseImpl(
    private val repository: SongRepository
): InsertAllSongToDbUseCase{
    override suspend fun invoke() {
        repository.insertAll()
    }
}