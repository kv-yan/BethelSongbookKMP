package am.bethel.application.settings.data.usecase

import am.bethel.application.common.domain.repository.SettingsRepository
import am.bethel.application.common.domain.repository.SongRepository
import am.bethel.application.settings.domain.usecase.InsertAllSongToDbUseCase
import kotlinx.coroutines.flow.first

class InsertAllSongToDbUseCaseImpl(
    private val repository: SongRepository,
    private val settingsRepository: SettingsRepository
) : InsertAllSongToDbUseCase {

    companion object {
        private const val CURRENT_DATA_VERSION = 4
    }

    override suspend fun invoke() {
        val dataVersion = settingsRepository.dataVersionFlow.first() // читаем однократно

        if (dataVersion < CURRENT_DATA_VERSION) {
            repository.insertAll()
            settingsRepository.setDataVersion(CURRENT_DATA_VERSION)
        }
    }
}
