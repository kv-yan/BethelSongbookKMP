package am.bethel.application.settings.data.usecase

import am.bethel.application.common.domain.repository.SettingsRepository
import am.bethel.application.common.domain.repository.SongRepository
import am.bethel.application.settings.domain.usecase.InsertAllSongToDbUseCase

class InsertAllSongToDbUseCaseImpl(
    private val repository: SongRepository,
    private val settingsRepository: SettingsRepository
) : InsertAllSongToDbUseCase {
    override suspend fun invoke() {
        settingsRepository.isFirstLaunchFlow.collect {
            if (!it) {
                println("songs already inserted")
                return@collect
            }
            else {
                settingsRepository.setIsFirstLaunch(false)
                repository.insertAll()
                println("songs inserted")
            }
        }
    }
}